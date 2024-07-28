package com.mrgao.xbqx.test.jvm;

/**
 * 栈内存溢出（Stack Overflow）
 * <p>
 * 栈内存溢出的原因：由于一个方法对应一个栈帧，因此栈帧过多就会导致内存溢出。
 * </p>
 * <p>
 * 虚拟机栈：
 * 1、每个线程运行所需要的内存，称为虚拟机栈。
 * 2、每个栈由多个栈帧（Frame）组成,对应着每次方法调用时所占用的内存。
 * 特点：先入后出
 * </p>
 * <p>
 * 栈内存相关面试题剖析
 * 1、垃圾回收是否涉及栈内存？
 * 答：不涉及，因为每个方法（栈帧）执行完会自动弹出，所以不会涉及垃圾的产生。
 * <p>
 * 2、栈内存分配越大越好吗？
 * 答：并不是，假设分配的物理内存时100M，每个线程栈的大小为1M，那么可以分配100个线程；但是如果提升线程栈的大小，那么可以分配对应的线程数就变少了。
 * linux系统默认就是1M，可以通过 -Xss进行大小的更改。
 * <p>
 * 3、方法内的局部变量是否线程安全？
 * 答：如果方法内局部变量没有逃离方法的作用访问，它是线程安全的；
 * 如果是局部变量引用了对象，并逃离方法的作用范围，需要考虑线程安全；
 * <p>
 * 4、栈内存溢出
 * 答：栈内存溢出的原因：由于一个方法对应一个栈帧，因此栈帧过多就会导致内存溢出，将抛出StackOverflowError异常。
 * 这种常见的情况：就是递归调用，不断产生新的栈帧，前面的栈帧不释放。
 * </p>
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    /**
     * 递归调用方法stackLeak()
     * 栈帧过多导致栈内存溢出
     * stack length:17698
     */
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * 递归调用方法stackLeak1()
     * 栈帧过大导致内存溢出，每个栈帧局部变量多一些
     * stack length:4451
     */
    public void stackLeak1() {
        long unused1, unused2, unused3, unused4, unused5,
                unused6, unused7, unused8, unused9, unused10,
                unused11, unused12, unused13, unused14, unused15,
                unused16, unused17, unused18, unused19, unused20,
                unused21, unused22, unused23, unused24, unused25,
                unused26, unused27, unused28, unused29, unused30,
                unused31, unused32, unused33, unused34, unused35,
                unused36, unused37, unused38, unused39, unused40,
                unused41, unused42, unused43, unused44, unused45,
                unused46, unused47, unused48, unused49, unused50,
                unused51, unused52, unused53, unused54, unused55,
                unused56, unused57, unused58, unused59, unused60,
                unused61, unused62, unused63, unused64, unused65,
                unused66, unused67, unused68, unused69, unused70,
                unused71, unused72, unused73, unused74, unused75,
                unused76, unused77, unused78, unused79, unused80,
                unused81, unused82, unused83, unused84, unused85,
                unused86, unused87, unused88, unused89, unused90,
                unused91, unused92, unused93, unused94, unused95,
                unused96, unused97, unused98, unused99, unused100;
        stackLength++;
        stackLeak1();
    }


    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            // 1、栈帧过多引发栈内存溢出
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + sof.stackLength);
            throw e;
        }
//        try {
//            // 2.栈帧多大引发栈内存溢出
//            sof.stackLeak1();
//        } catch (Throwable e) {
//            System.out.println("stack length:" + sof.stackLength);
//            throw e;
//        }

    }
}
