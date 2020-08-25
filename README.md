# Operating-Systems
 Operating Systems Internals and Design principles 8th 读书笔记，资源整理

<!--more-->

### 操作系统设计的三个目标：

- 便利性
- 有效性
- 扩展能力

### 操作系统运行时有哪两种模式：

- 作为硬件层与应用层之间的一个接口，也就是通常意义上硬件与软件之间的缓冲层。

- 作为资源管理器，管理计算机资源（IO，内存，等等）

  - Functions in the same way as ordinary computer software

  - Program, or suite of programs, executed by the processor

  - Frequently relinquishes control and must depend on the processor to allow it to regain control

![](https://github.com/fengxiaohu/Operating-Systems/blob/master/Operating-System/2_1.JPG)



### 操作系统发展

- Serial Processing 串行处理

- Simple Batch Systems 简单批处理

- Multiprogrammed Batch Systems 多道批处理系统

- Time Sharing Systems 分时系统

  |                                          | Batch Multiprogramming | Time sharing                     |
  | ---------------------------------------- | ---------------------- | -------------------------------- |
  | principal objective                      | Maximize processor use | Minimize response time           |
  | Source of directives to operating system | Job control language   | commmands entered at the termial |

  



### 与串行处理相比，多道程序设计有哪些优势

基于cpu执行指令的速度远远超过了I/O设备的处理速度，在程序执行的整个生命周期中，大量的时间被浪费在了等待I/O操作上。


![](https://github.com/fengxiaohu/Operating-Systems/blob/master/Operating-System/2_3.JPG)

多道批处理系统通过一次将准备运行的多个job载入内存中，当一个正在执行中的job需要I/O时，可以转去执行另外一个job。这种近似于并发执行的方式大大提高了程序执行的速率。

![](https://github.com/fengxiaohu/Operating-Systems/blob/master/Operating-System/2_4.JPG)

此外，由于一次将准备运行的多个job载入内存，进一步需要内存管理，同时在多个job间进行切换也需要使用调度算法。

### 什么是进程

一个正在执行中程序的实例。
![](https://github.com/fengxiaohu/Operating-Systems/blob/master/Operating-System/2_5.jpg)

同时进程还包括了以下三个组件

- 可执行程序
- 与此可执行程序相关的数据（变量，缓冲，等）
- 程序状态/执行上下文 process state/ execution context 

### 操作系统是怎样使用进程的执行上下文的

进程上下文（The execution context/process state） 是操作系统管理和控制进程的内部数据。 execution context是与进程本身分开的，避免操作系统直接被进程所访问到。进程上下文包含了两部分信息，一部分是管理进程相关的信息，另外一部分是处理器执行进程所需要的信息。

### 列出并简要介绍五种操作系统中的内存管理功能

- 进程隔离 

  操作系统必须保证每个进程不会干扰到其他进程的内存空间

- 自送分配与管理 

  动态分配内存

- 模块化编程

- 保护与访问控制

- 永久存储

### 解释实地址和虚地址的区别

实地址就是物理地址，是程序在内存中的实际地址

虚拟地址是在虚拟内存中的地址

### 描述时间片轮转调度技术

为每个进程分配一定的时间片，通过环形队列

### 说明单内核与微内核之间区别

单内核架构（**monolithic kernel** ）会将OS所提供的大部分功能集中在内核实现，包括

- scheduling
- file system
- networking
- device drivers
- memory management

典型情况下，这个大内核是作为一个进程实现的，所有元素都共享相同的地址空间

微内核架构（microkernel architecture）仅仅将一些必要的功能放在内核中实现

- address spaces, interprocess
- communication (IPC)
- basic scheduling

其他的一些服务由进程来提供，运行在用户态。极大地提升了操作系统的可定制性，用户可以根据不同的场景和应用来定制一些模块使用。在分布式系统有很大优势。

### 什么是多线程

多线程（**Multithreading**）是可以将一个进程分成许多线程，然后并发运行这些线程



• **Thread:** A dispatchable unit of work. It includes a processor context (which

includes the program counter and stack pointer) and its own data area for a

stack (to enable subroutine branching). A thread executes sequentially and is

interruptible so that the processor can turn to another thread.



• **Process :** A collection of one or more threads and associated system resources

(such as memory containing both code and data, open files, and devices). This

corresponds closely to the concept of a program in execution. By breaking

a single application into multiple threads, the programmer has great control

over the modularity of the application and the timing of application-related

events.

![](https://github.com/fengxiaohu/Operating-Systems/blob/master/Operating-System/2_6.JPG)

多线程技术面对的是同时运行许多互相独立的应用的场景。典型的例子如数据库。



## 进程描述与控制

### 什么是进程的轨迹（trace）

将单个进程的执行抽象为一系列的二进制的指令，这样我们就可以直观地从地址的变化上观察程序的执行。

![](https://github.com/fengxiaohu/Operating-Systems/blob/master/Operating-System/3.2.JPG)

![](https://github.com/fengxiaohu/Operating-Systems/blob/master/Operating-System/3.3.JPG)

### 基于下图简单描述五个状态

![](https://github.com/fengxiaohu/Operating-Systems/blob/master/Operating-System/3.4.JPG)




