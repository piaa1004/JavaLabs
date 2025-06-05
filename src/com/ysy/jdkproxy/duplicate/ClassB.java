package com.ysy.jdkproxy.duplicate;

class ClassB implements InterfaceB {
    @Override
    public void duplicateMethods() {
        System.out.println("ClassB: duplicateMethods");
    }
}