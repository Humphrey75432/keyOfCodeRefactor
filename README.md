<font face="DengXian" size="4">

# 代码重构之道中的示例程序
1. 使用VCS追溯代码的演进过程以及重构技巧；
2. 掌握代码重构的基本方法与要领；
3. 了解常用的重构方式以及技巧；

## 重构的实践原则
1. 重构的本质，由于每次改动的幅度都很小，所以任何错误都很容易被发现，重构前应当确保系统中有测试环境保障；
2. 任何一个傻瓜都能写出计算机可以理解的代码。唯有写出人类容易理解的代码，才是优秀的程序员
3. 重构时不必太过于关注性能优化，这是在重构结束后优化需要完成的工作；
4. 引入设计模式来重构代码的好处是既能使得函数功能模块化，而且随着业务需求的不断变化，可扩展性也会变得越来越容易，虽然在短期可能会增加代码，但这样做是值得的；
5. 三次原则：第一次去做某件事情的时候尽管去做；第二次做类似的事情会产生反感；但无论如何还是可以去做，第三次再做类似的事情，你就必须去重构了；
6. 保留旧接口，擅用@Deprecated注解，千万不要将源代码做份拷贝，结局是自己会被大块重复代码坑死；
7. 如果试着做点测试发现程序满是错误无法正常执行下去，那么就可以放弃重构直接重写了；
8. 项目接近交付期限时应当避免重构，未完成的任务就好比债务，欠下的债务必然要付出利息；
9. 哪怕你完全了解系统，也请实际度量它的性能，不要臆测。
10. 重构不能以降低性能为前提，当然很多时候是无法保证的，因此需要准备一个度量工具来监控程序的运行；

## 重构技巧
1. 找出代码的逻辑泥团并使用Extract Method方法进行抽取；
2. 任何不会被修改的变量都可以当成参数并传入新的函数；
3. 绝大多数情况下，函数应该放在它所使用数据的所属对象内；
4. 将不会再改变的变量进行替换；
5. 最好不要在另一个对象的属性上运用switch语句。如果不得已使用，也应该在对象自己的数据上使用；

## 重构手段
1. Extract Method
1.1 Extract Class
1.2 Extract SubClass
1.3 Extract Interface
2. Move Method
3. Rename Variable
4. Replace Temp with Query
5. Replace Type Code with State/Strategy（将类型相关的行为搬移之State或者Strategy中）
5.1 Replace Type Code with SubClass
6. Replace Condition with Polymorphism（去除switch语句）
7. Replace Paramenter with Method
8. Preserve Whole Object
9. Collapse Hierarchy

## 编码中常见的一些坏味道
### 1 Duplicate Code
* 同一个类的两个函数存在相同的表达式 --> 使用Extract Method提取重复代码并合二为一。
* 两个互为兄弟的子类包含相同的表达式 --> 对互为兄弟的两个子类使用Extract Method提取重复代码，再使用Pull up Method将其推入超类中；
* 代码逻辑类似并非完全相同：使用Extract Method将差异部分切割开，构成单独一个函数。此处可能可以使用Form Template Method组成一个Template Method设计模式。如果有些函数以不同的算法做相同的事情，可以选择其中一个较为清晰的一个，并使用Substitute Algorithm将其他函数的算法替换掉。
* 两个毫不相关的类出现Duplicate Code，考虑对其中一个使用Extract Method并将重复代码提炼到一个独立类中，然后在另一个类中使用这个新类。此处要考虑新类在后续重构中不会再出现。

### 2 Long Method
* 每当感觉需要以注释来说明点什么的时候，我们就把需要说明的东西写进一个独立的函数中，并以其用途（而非实现手法）命名。
* 99%的场合只需要使用Extract Method方法，找到适合集中在一起的部分，将它们提炼出来行程一个函数。
* 如果函数内存在大量的参数和临时变量，可以尝试使用Replace Temp with Query来消除临时变量；Introduce Paramemeter Object和Preserve Whole Object则可以将过长的参数列变得更简洁一些。
* 条件表达式和循环体常常也是提炼函数的信号，你可以使用Decompose Conditional处理条件表达式。至于循环，你应该将循环和其内的代码提炼到一个独立函数中。

### Large Class
* 可以使用Extract Class和Extract SubClass方法将类中的代码进行抽取整理。
* Extract Interface：先确定客户端如何使用它们，然后运用Extract Interface为每一种使用方式提炼出一个接口。这样可以帮助你看清楚如何分解这个类。

### Large Parameter List
* 如果向已有的对象发出一条请求就可以取代一个参数，那么应该激活重构手法Replace Parameter with Method，如果往函数传入一个对象可以解决大部分需求，那么使用Preserve Whole Object将来自同一对象的一堆数据收集起来，并以该对象去替换。
* 如果某些数据缺乏合理的对象归属，可以使用Introduce Paramenter Object为它们制造一个“参数对象”。
* 此处要注意“被调用对象”与“较大对象”之间的依赖关系，由于传参太长或者频繁变化，这种依赖关系可能导致重构效果适得其反。

### Divergent Change（分散变化）
* 针对某一外界变化的所有相应修改，都只应该发生在单一类中，而这个新类内的所有内容都应该反应此变化。为此，你应该找出某特定原因而造成的所有变化，然后运用Extract Method将其提炼到另一个类中。

### Shotgun Surgery（霰弹式修改）
* 如果每遇到某种变化，你都必须在许多不同的类内做出许多小修改，你说面临的坏味道就是Shotgun Surgery。通俗点就是如果你要修改的东西散落在代码的四处，你不但很难找到它们，也很容易忘记某个重要修改。
* 为了解决这种变化，你可以使用Move Method和Move Field把所有需要修改的代码放进同一个类。如果眼下没有合适的类安置这些代码，就创造一个。通常可以使用Inline Class把一系列相关行为放进同一个类。这可能会造成少量Divergent Change，但是可以轻易处理。

### Feature Envy（依恋情结）
* 函数对某个类的兴趣高过对自己所处的类的兴趣。这种情况的焦点通常就是数据。
* 简单的操作就是考虑Extract Method和Move Method来给函数搬家；
* 如果一个函数使用到多个类的功能，判断函数的安置原则：判断哪个类拥有最多被次函数使用的数据，然后就把这个函数和那些数据摆在一起。如果先以Extract Method将这个函数分解为数个较小函数并分别放置于不同地点。
* 比较有意思的是：设计模式中的Strategy和Visitor模式正好破坏了这一原则，使用原则是：数据和引用这些数据的行为总是一起变化的，但也有例外。如果出现例外我们就搬移行为，保持变化只在一地发生。

### Data Clupms
* 多个类中字段、许多函数签名中相同的参数。这些总是绑在一起的数据应该拥有属于自己的对象。
* 找出上述字段出现的地方并将其提炼到一个独立对象中。然后将注意力转移到函数签名上，用Introduce Parameter Object或Preserve Whole Object瘦身，好处在于缩减参数列表，简化函数调用。

### Primitive Obsession（基本类型偏执）
* 利用Replace Data Value With Object将原本单独存在的数据值替换成对象（用币种和数值组成Money类、用电话号码或者邮政编码组成特殊字符串）。
* 如果想要替换的数据值是类型码或者枚举，可使用Replace Type Code with SubClass或Replace Type Code with State/Strategy加以处理。
* 如果有一组应该总是被放在一起的字段，可使用Extract Class。如果你在参数列表中看到基本数据类型，不妨试试Introduce Paramenter Object。
* 如果发现从数据中筛选数据，可运用Replace Array with Object

### Switch Statements
* 条件反射 --> 一看到switch 语句就要考虑使用多态来替换，问题在于如何辨识与类型相关的函数或者类，应该使用Extract Method将switch提炼到一个独立函数中，再以Move Method将其搬移到需要多态性的类中。
* 完成上述步骤后，需要决定是否使用Replace Type Code with Subclass或Replace Type Code with State/Strategy。
* 完成第二步之后，搭建好上述的集成结构就可以使用Replace Conditional with Polymorphism
* 单一函数内的选择实例，使用Replace Method with Explicit Methods，如果选择条件之一是null，可以试试Introduce Null Object。

### Parallel Inheritance Hierarchies（平行继承体系）
* 每当你为某个类增加一个子类，必须也为另一个类增加相应地子类。
* 某个继承体系的类名称前缀和另一个继承体系的类名称前缀完全相同，解决办法是让一个继承体系的实例引用另一种继承体系的实例。配合Move Method和Move Field方法。

### Lazy Class（冗余类）
* 对于重构中那些功能和职责缩水的类予以剔除，使用Collapse Hierarchy方法处理那些几乎没用的组件，以Inline Class对付。

### Speculative Generality（夸夸其谈未来性）
* 功能未扩展到未来而引入的坏道。

</font>