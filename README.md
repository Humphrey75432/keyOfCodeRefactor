# 代码重构之道中的示例程序
1. 使用VCS追溯代码的演进过程以及重构技巧；
2. 掌握代码重构的基本方法与要领；
3. 了解常用的重构方式以及技巧；

## 重构的实践原则
1. 重构的本质，由于每次改动的幅度都很小，所以任何错误都很容易被发现
2. 任何一个傻瓜都能写出计算机可以理解的代码。唯有写出人类容易理解的代码，才是优秀的程序员

## 重构技巧
1. 找出代码的逻辑泥团并使用Extract Method方法进行抽取；
2. 任何不会被修改的变量都可以当成参数并传入新的函数；
3. 绝大多数情况下，函数应该放在它所使用数据的所属对象内；
4. 将不会再改变的变量进行替换；

## 重构手段
1. Extract Method
2. Move Method
3. Rename Variable
4. Replace Temp with Query