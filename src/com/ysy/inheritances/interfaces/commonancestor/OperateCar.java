package com.ysy.inheritances.interfaces.commonancestor;

public interface OperateCar {
	
	default public int startEngine() {
        return 0;
    }

}
