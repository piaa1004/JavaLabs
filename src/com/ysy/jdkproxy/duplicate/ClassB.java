package com.ysy.jdkproxy.duplicate;

class ClassB implements InterfaceB {
    @Override
    public void duplicateMethod() {
        System.out.println("ClassB: duplicateMethod");
    }
}