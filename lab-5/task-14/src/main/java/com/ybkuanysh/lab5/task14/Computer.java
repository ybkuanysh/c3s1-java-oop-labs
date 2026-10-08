package com.ybkuanysh.lab5.task14;

public class Computer {
    private final String name;
    private Os os;
    private Processor processor;
    private Ram ram;

    public Computer(String name) {
        this.name = name;
    }

    public class Os {
        private final String title;
        private final String version;

        public Os(String title, String version) {
            this.title = title;
            this.version = version;
        }

        public void install() {
            os = this;
            System.out.println(name + ": установлена ОС " + this);
        }

        @Override
        public String toString() {
            return title + " " + version;
        }
    }

    public class Processor {
        private final String model;
        private final int cores;
        private final double ghz;

        public Processor(String model, int cores, double ghz) {
            this.model = model;
            this.cores = cores;
            this.ghz = ghz;
        }

        public void install() {
            processor = this;
            System.out.println(name + ": установлен процессор " + this);
        }

        @Override
        public String toString() {
            return model + " (" + cores + " ядер, " + ghz + " ГГц)";
        }
    }

    public class Ram {
        private int sizeGb;

        public Ram(int sizeGb) {
            this.sizeGb = sizeGb;
        }

        public void install() {
            ram = this;
            System.out.println(name + ": установлена память " + this);
        }

        public void upgrade(int extraGb) {
            sizeGb += extraGb;
            System.out.println(name + ": память увеличена до " + this);
        }

        @Override
        public String toString() {
            return sizeGb + " ГБ";
        }
    }

    public static class Requirements {
        public static final int MIN_RAM_GB = 8;
        public static final int MIN_CORES = 4;

        public static boolean check(Computer c) {
            return c.ram != null && c.processor != null
                    && c.ram.sizeGb >= MIN_RAM_GB && c.processor.cores >= MIN_CORES;
        }
    }

    public interface Task {
        void run(Computer c);
    }

    public void execute(Task task) {
        task.run(this);
    }

    @Override
    public String toString() {
        return name + " [ОС: " + os + ", CPU: " + processor + ", RAM: " + ram + "]";
    }
}
