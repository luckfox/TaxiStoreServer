package com.utils.test;

import com.google.gson.Gson;

class GsonExample {
    public static void main(String[] args) {
        // 创建一个 Gson 实例
        Gson gson = new Gson();

        // 创建一个示例对象
        User user = new User("John Doe", 25);

        // 将对象转换为 JSON
        String json = gson.toJson(user);
        System.out.println("JSON: " + json);
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters (可选)
}
