package com.winter.studything.utils;

import java.util.UUID;

public class Uuid {
    public static void main(String[] args) {
        for (int i = 0; i < 250; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            System.out.println(uuid);
//            System.out.println(uuid.length());
        }
    }
}