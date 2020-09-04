
// Filename: peterson_spinlock.c 
// Use below command to compile: 
// gcc -pthread peterson_spinlock.c -o peterson_spinlock 
  
#include <stdio.h> 
#include <pthread.h> 

  
int flag[2];
int turn;
const int MAX = 1e9;
int ans = 0;

void lock_init()
{
    flag[0] = flag[1]=0;
    turn = 0;
}
void lock(int self)
{
    flag[self] = 1;//lock
    turn = 1-self;// first give the other thread the chance to 
    // acquire lock 
    while(flag[1-self]==1 && turn == 1-self);// exit until 
    
}
void unlock(int self)
{
    flag[self] =0;
}
void* func(void *s)
{
    int i=0;
    int self = (int*)s;
    printf("thread Entered:%d\n",self);
    lock(self);
    
    for(i=0;i<MAX;i++)
    {
        ans++;
        unlock(self);
    }
}
int main()
{
    pthread_t p1,p2;
    lock_init();
    pthread_create(&p1,NULL,func,(void*)0);
    pthread_create(&p2,NULL,func,(void*)1);
    pthread_join(p1,NULL);
    pthread_join(p2,NULL);
    printf("Actual Count: %d | Expected Count: %d\n", 
                                        ans, MAX*2); 
    return 0;
}

