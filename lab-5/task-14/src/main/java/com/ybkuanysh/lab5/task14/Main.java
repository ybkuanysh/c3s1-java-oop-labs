package com.ybkuanysh.lab5.task14;

public class Main {
    public static void main(String[] args) {
        Computer pc = new Computer("Office PC");
        pc.new Processor("Intel Core i3", 2, 3.6).install();
        Computer.Ram ram = pc.new Ram(4);
        ram.install();
        pc.new Os("Windows", "11").install();
        System.out.println(pc);
        System.out.println("Соответствует требованиям: " + Computer.Requirements.check(pc));

        ram.upgrade(8);
        pc.new Processor("Intel Core i5", 6, 4.2).install();
        System.out.println(pc);
        System.out.println("Соответствует требованиям: " + Computer.Requirements.check(pc));

        pc.execute(new Computer.Task() {
            @Override
            public void run(Computer c) {
                System.out.println("Диагностика: " + c + " — OK");
            }
        });
    }
}
