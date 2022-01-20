1: На какие две группы разделяются классы, объявленные внутри другого класса?
Как они называются на инглише?  
**Ответ.** Язык программирования Java позволяет вам определять класс внутри другого класса. Такой класс называется вложенным классом(nested class).
Вложенные классы делятся на две категории:  
a) Non-static nested classes — нестатические вложенные классы. По-другому их еще называют inner classes — внутренние классы.  
б) Static nested classes — статические вложенные классы.  
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html

2: Для каких целей они используются?  
**Ответ.**  
Веские причины для использования вложенных классов включают следующее:  
а) Это способ логической группировки классов, которые используются только в одном месте : если класс полезен только для одного другого класса, то логично встроить его в этот класс и сохранить их вместе. Вложение таких «вспомогательных классов» делает их пакет более упорядоченным.  
б) Это увеличивает инкапсуляцию : рассмотрим два класса верхнего уровня, A и B, где B требуется доступ к членам A, которые в противном случае были бы объявлены private. Скрывая класс B внутри класса A, члены A могут быть объявлены закрытыми, и B может получить к ним доступ. Кроме того, сам Б может быть скрыт от внешнего мира.  
в) Это может привести к более читаемому и удобному для сопровождения коду : вложение небольших классов в классы верхнего уровня помещает код ближе к тому месту, где он используется.  
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  

3: Какие уровни доступа применяются к таким классам?  
**Ответ.** вложенный класс может быть объявлен private, public, protected или package private.  
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  

4: Какие существуют варианты внутренних классов?  
**Ответ.** В свою очередь, внутренние классы (inner classes) имеют два особых подвида. Помимо того, что внутренний класс может быть просто внутренним классом, он еще бывает:  
а) локальным классом (local class)
б) анонимным классом (anonymous class)  
**Источник.** https://javarush.ru/groups/posts/2181-vlozhennihe-vnutrennie-klassih  

5: Пусть объявлен класс Outer, а внутри него публичный вложенный класс NestedPublic и публичный внутренний класс InnerPublic. Создайте экземпляры каждого класса:
а) внутри класса Outer,
б) извне класса Outer?  
**Ответ.**  
Важно понмать что объекты, которые являются экземплярами внутреннего класса, существуют внутри экземпляра внешнего класса.  
а) создание объекта статического вложенного класса выглядит также как и внутреннего.  
```java
NestedPublic nestedPublic = new NestedPublic();
```
б)  Использование объекта внутреннего класса вне своего внешнего класса возможно только при наличии доступа (видимости) и при объявлении ссылки в виде:  
```java
Outer.InnerPublic innerObject = new Outer().new InnerPublic ();
Outer.NestedPublic nestedOject = new Outer.NestedPublic ();
```

6: Пусть объявлен класс Outer, а внутри него приватный вложенный класс NestedPrivate и приватный внутренний класс InnerPrivate. Создайте экземпляры каждого класса:
а) внутри класса Outer,
б) извне класса Outer?  
**Ответ.**  
а) Внутри класса Outer механизм создания экземпляров вложенных классов такой же как и модификатором доступа public:  
```java
public class Outer {
private static class NestedPrivate { 
}
private class InnerPrivate { 
}
NestedPrivate nestedPrivate = new NestedPrivate();
InnerPrivate innerPrivate = new InnerPrivate();
}
```
б) Извне класса Outer нельзя создать экземляры вложенных приватных классов.  

7: Пусть объявлен класс Outer, а внутри него внутренний класс Inner. Как обратиться внутри класса Inner:
а) к экземпляру класса Inner,
б) к объемлющему экземпляру класса Outer?  
**Ответ.**  
а)  К экземпляру класс Inner можно обратиться через ключевое слово this так же к экземпляру внутреннего класса, созданному вне этого класса, обратиться можно и по объектной ссылке.  
б) Если вам понадобится получить ссылку на объект внешнего класса, запишите имя внешнего класса, за которым следует точка, а затем ключевое слово this.  
Пример:  
```java
public class Outer {
    public class Inner {
        public void showClass(){
            System.out.println(this);
            System.out.println(Outer.this);
        }
        @Override
        public String toString() {
            return "It's inner class";
        }
    }

    @Override
    public String toString() {
        return "It's outer class";
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.showClass();
    }
}
```
Вывод будет следующим:
Inner class
Outer class  

8: Пусть объявлен класс Outer, а внутри него вложенный класс Nested. Как обратиться внутри класса Nested:
а) к экземпляру класса Nested,
б) к объемлющему экземпляру класса Outer?  
**Ответ.**  
а) К экземпляру класс Nested можно обратиться через ключевое слово this.  
б) Однако обращение к внешнему классу с помощью Outer.this приведет к ошибке. Т.к. класс должен обращаться к нестатическим членам своего внешнего класса при помощи объекта, т.е. он не может обращаться напрямую на нестатические члены своего внешнего класса.  
Пример:  
```java
public class Outer {

    public static class Nested {
        public void show(){
            System.out.println(this);
            System.out.println(Outer.this); // Error: 'Outer.this' cannot be referenced from a static context
        }
        @Override
        public String toString() {
            return "It's nested class";
        }
    }

    @Override
    public String toString() {
        return "It's outer class";
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Nested nested = new Nested();
        nested.show();
    }
}
```

9: Можно ли из вложенного класса обратиться к членам внешнего класса?
Если да, то приведите пример.  
**Ответ.** Вложенный класс является членом окружающего его класса. Нестатические вложенные классы (внутренние классы) имеют доступ к другим членам включающего класса, даже если они объявлены закрытыми. Статические вложенные классы не имеют доступа к другим членам окружающего класса.  
Пример:  
```java
public class Outer {
   String outerField = "Внешнее поле";
   static String staticOuterField = "Статическое внешнее поле";
   public static class Nested {
      public void show(Outer outer){
         // System.out.println (outerField);  Compiler error: Cannot make a static reference to the non-static field outerField
         System.out.println(outer.outerField);
         System.out.println(staticOuterField);
      }
   }
}
```
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  

10: Можно ли из внутреннего класса обратиться к экземпляру внешнего класса?
Если да, то приведите пример.  
**Ответ.** Да, к экземпляру класс Inner можно обратиться через ключевое слово this.  
Пример:
```java
public class Outer {
   public class Inner {
      public void show(){
         System.out.println(this);
         System.out.println(Outer.this);
      }
   }
}
```
**Источник.** http://developer.alexanderklimov.ru/android/java/innerclass.php   

11: Можно ли определить экземпляр вложенного класса, не определяя экземпляры внешнего класса?
Если да, то приведите пример.  
**Ответ.**  
Создать экземпляр статического вложенного класса во внешнем классе можно так же, как и класс верхнего уровня:  
```java
NestedPublic nestedObject = new NestedPublic;
```
Использование объекта вложенного класса вне своего внешнего класса возможно только при наличии доступа (видимости) и при объявлении ссылки в виде:  
```java
Outer.NestedPublic nestedOject = new Outer.NestedPublic ();
```
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html  

12: Есть ли ограничения на объявление локальных переменных в локальных внутренних классах?
Есть ли да, то какие?  
**Ответ.**  
Да, локальный класс может обращаться к локальным переменным и параметрам включающего блока, которые являются окончательными или фактически окончательными . Переменная или параметр, значение которых никогда не изменяется после инициализации, фактически являются окончательными. Они не могут иметь внутри себя статических объявлений (полей, методов, классов); исключением являются константы (static final); Однако, начиная с Java SE 8, локальный класс может обращаться к локальным переменным и параметрам включающего блока, которые являются окончательными или effectively final . Переменная или параметр, значение которых никогда не изменяется после инициализации, фактически является окончательным.  
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html  

13: Можно ли наследовать вложенные классы?
Если да, то приведите пример.  
**Ответ.** Да, вложеннные классы имеют право наследовать другие классы, реализовывать интерфейсы и выступать в роли объектов наследования. Однако вложенного класса не способен унаследовать возможность доступа к членам внешнего класса, которыми наделен его суперкласс. Допустимо наследование следующего вида:  
```java
public class SubStudent extends Student {
   // code
   public class SubAddress extends Address {
      // code
   }
}
```
**Источник.** И.Н. Блинов В.С. Романчик Java from EPAM : учеб.-метод. пособие (Четыре четверти, 2020.) стр. 162.  
http://bsac.by/projects/eemc/java/theory/files/L5.pdf стр. 139.  

14: Можно ли из подкласса обратиться к методу вложенного суперкласса?
Если да, то приведите пример.  
Да, механизм наследования позволяет потомку пользоваться методом вложенного суперкласса.
Пример:  
```java
public class Student {
    public class Address {
        public void someMethod(){
            
        }
    }
    public class SubStudent extends Student {

    }
    public class SubAddress extends Address {
        public class FreeAddress extends Student.Address {
            public void checkParentMethod (){
                someMethod();
            }
        }
    }
}
```
**Источник.** И.Н. Блинов В.С. Романчик Java from EPAM : учеб.-метод. пособие (Четыре четверти, 2020.) стр. 162.  

15: Какие существуют варианты внутренних интерфейсов?  
**Ответ.** Интерфейс может быть объявлен членом класса или другого интерфейса. Такой интерфейс называется интерфейсом-членом или вложенным интерфейсом. Вложенный в класс интерфейс может быть объявлен как public, private или protected. Это отличает его от интерфейса верхнего уровня или интерфейса вложенного в другой интерфейс, который должен быть либо объявлен как public, либо, как уже было отмечено, должен использовать уровень доступа, заданный по умолчанию. Поскольку интерфейс не может быть создан, внутренний интерфейс имеет смысл, только если он статичен. Поэтому по умолчанию внутренний интерфейс является статическим, независимо от того, добавлено ли ключевое слово static вручную.  
**Источник.** http://pr0java.blogspot.com/2015/07/5.html  

16: Можно ли объявить класс внутри интерфейса?
Если да, то есть ли ограничения? Приведите пример.  
**Ответ.**  
Если функциональность класса тесно связана с интерфейсом, и мы не собираемся использовать этот класс где бы то ни было, мы можем определить класс внутри интерфейса.  
Пример:  
```java
package PracticeTest;

public interface VehicleService {
   public void repair(Vehicle v);
   public class Vehicle{
      String vehicleModel;
      String vehicleNumber;
      public Vehicle(String vehicleModel, String vehicleNumber) {
         super();
         this.vehicleModel = vehicleModel;
         this.vehicleNumber = vehicleNumber;
      }

   }
}
```

17: Можно ли создать экземпляр анонимного класса на основе:
а) абстрактного класса?
б) интерфейса?
в) неабстрактного класса?
г) финального класса?
Если да, то приведите пример.  
**Ответ.**  
а) Да, несмотря на то что создавать экзелмпляр интерфейса мы не можем, мы можем создать объект экземпляр анонимного класса на основе абстрактного класса. (Смотреть пример).  
б) Да, несмотря на то что создавать экзелмпляр интерфейса мы не можем, мы можем создать объект экземпляр анонимного класса который реализует наш интерфейс. (Смотреть пример).  
в) Да. Анонимный класс будет расширять класс SomeClass и может переопределять методы предка (Смотреть пример).  
г) Нет. Конструктор анонимного класса определить невозможно. Нельзя создать анонимный класс для final-класса.  
```java
public class Main {
        public static void main(String[] args) {
            Colorable colorable = new Colorable() {   //create an instance of an anonymous class that implements the interface Colorable
                @Override
                public String getColor() {
                    return "Red";
                }
            };
            Color color = new Color() {    //create an instance of an anonymous class based on the abstract class Color
                @Override
                String getColor() {
                    return "Blue";
                }
            };
            SomeClass someClass = new SomeClass() { //create an instance of an anonymous class based on the class SomeClass
                @Override
                public void someMethod() {
                    System.out.println("New text");
                }
            };
            SomeClass1 someClass1 = new SomeClass1() {   //Error: cannot inherit from final SomeClass1
                @Override
                public void someMethod() {
                    System.out.println("New text");
                }
            };
        }
    }
interface Colorable {
    String getColor();
}
abstract class Color {
    abstract String getColor();
}
class SomeClass {
    public void someMethod(){
        System.out.println("Some text");
    }
}
final class SomeClass1 {
    public void someMethod(){
        System.out.println("Some text");
    }
}
```

18: Дан следующий java-файл.
```java
//-------------- begin --------------
class Runner {
public static void main(String[] args){
Something something = new Something();
something.doSomething(...);		//1
}
}
interface Smthable {
void doSmth();
}
class Something {
void doSomething(...) {			//2
smth.doSmth();
}
}
//--------------- end ---------------
```
1. Замените многоточия в строках 1 и 2 на такой код, чтобы приложение после запуска с помощью экземпляра анонимного класса, порожденного от интерфейса Smthable, вывело на консоль текст Hello, World.
2. Получите тот же результат, переместив:
   а) интерфейс Smthable внутрь класса Something,
   б) класс Something внутрь интерфейса Smthable.  
**Ответ.**  
1:
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args){
        Something something = new Something();
        something.doSomething(new Smthable() {
            @Override
            public void doSmth() {
                System.out.println("Hello, World");
            }
        });                //1

    }
}
interface Smthable {
    void doSmth();
}
class Something {
    void doSomething(Smthable smth) {                        //2
        smth.doSmth();
    }
}
//--------------- end ---------------
```   
2:  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args){
        Something something = new Something();
        something.doSomething(new Something.Smthable() { //1
            @Override
            public void doSmth() {
                System.out.println("Hello, World");
            }
        });
    }
}
class Something {
    interface Smthable {
        void doSmth();
    }
    void doSomething(Smthable smth) {                        //2
        smth.doSmth();
    }
}
//--------------- end ---------------
```

19: Дан следующий java-файл.
```java
//-------------- begin --------------
abstract class AbstractRunner {
abstract int getYear();
abstract class AbstarctInner {
abstract int getYear();
}
public static void main(String[] args) {
... //1
... //2
... //3
}
}
//--------------- end ---------------
```
Создайте в строке 1 ссылку runner на экземпляр подкласса класса AbstractRunner.
Создайте в строке 2 ссылку inner на экземпляр подкласса класса AbstractInner.
Выведите на консоль в строке 3 текст 2010;2015, используя данные ссылки.  
**Ответ.**  
```java
//-------------- begin --------------
abstract class AbstractRunner {
    abstract int getYear();
    abstract class AbstarctInner {
        abstract int getYear();
    }
    public static void main(String[] args) {
        AbstractRunner runner = new AbstractRunner() {   //1
            @Override
            int getYear() {
                return 2010;
            }
        };
        AbstractRunner.AbstarctInner inner = runner.new AbstarctInner() {   //2
            @Override
            int getYear() {
                return 2015;
            }
        };
        System.out.println(runner.getYear() + ";" + inner.getYear());   //3
    }
}
```

20: Дан следующий java-файл.
```java
//-------------- begin --------------
class Runner {
public static void main(String[] args) {
... 	//1
}
}
class Outer {
class Inner {
void go() {
System.out.println("Gone!");
}
}
}
//--------------- end ---------------
```
1. С помощью функционала классов Outer и Inner выведите на консоль в строке 1 текст Gone!.
2. Переместив класс Outer внутрь класса Runner, получите тот же результат:
   а) не изменяя строку 1.
   б) изменяя строку 1,  
**Ответ.**  
   1:  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
        new Outer().new Inner().go();   //1
    }
}
class Outer {
    class Inner {
        void go() {
            System.out.println("Gone!");
        }
    }
}
//--------------- end ---------------
```   
2:  
a)  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
    }
}
class Outer {
    public static void main(String[] args) {
        new Outer().new Nested().go();
    }
    class Inner {
        void go() {
            System.out.println("Gone!");
        }
    }
}

//--------------- end ---------------

```
б)  
```java
//-------------- begin --------------
class Runner {
    public static void main(String[] args) {
        new Outer().new Nested().go();
    }
}
class Outer {
    class Inner {
        void go() {
            System.out.println("Gone!");
        }
    }
}
//--------------- end ---------------
```

21: Что представляют собой элементы перечисления?
Подсказка. Откомпилируйте фабричный класс из задачи inheritance1 и посмотрите, какие получились .class-файлы.  
**Ответ.** Перечисление — это класс. Объявляя enum мы неявно создаем класс производный от java.lang.Enum. Элементы перечисления — экземпляры enum-класса, доступные статически Элементы enum Season (WINTER, SPRING и т.д.) — это статически доступные экземпляры enum-класса Season. Их статическая доступность позволяет нам выполнять сравнение с помощью оператора сравнения ссылок ==. Элементы перечисления в свою очередь являются полноценными констатными вложенными классами. Пример:  
```java
enum Day{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
...
Season season = Season.SUMMER;
if (season == Season.AUTUMN) season = Season.WINTER;
```
**Источник.**  https://javarush.ru/groups/posts/1419-perechislenija-v-java-java-enum  
Практическая задача Inheritace1: Документ: Inheritance1-review  

22: Как образуются имена вложенных и внутренних .class-файлов после компиляции?
Приведите примеры.  
**Ответ.** Внутренние классы (включая локальные и анонимные) привязаны к конкретному объекту внешнего класса. Чтобы этого достичь, во внутренний класс компилятором добавляется новое final-поле (обычно с именем this$0), которое содержит ссылку на окружающий класс. При этом в каждый конструктор добавляется соответствующий параметр.  
Рассмотрим пример:  
```java
public class Ship {
 // поля и конструкторы внешнего класса
 public class Engine { // определение внутреннего класса
 // поля и методы
 public void launch() {
 System.out.println("Запуск двигателя");
 } 
 } // конец объявления внутреннего класса
 // методы внешнего класса
}
```
При компиляции создается объектный модуль, соответствующий внутреннему классу, который получит имя Ship$Engine.class.  

**Источник.** И. Н. Блинов В. С. Романчик Java Методы программирования Учебно-методическое пособие 2013 стр. 133.  

23: Может ли вложенный класс быть раннер-классом?
Если да, то приведите пример, иначе поясните, почему нет.  
**Ответ.** Вложенный класс может содержать статические методы, соотвественно метод public static void main может быть определен во вложенном классе. Таким образом вложенный класс может быть раннер-классом и данный код будет работать:  
```java
class FakeRunner {
    static class RealRunner {
        public static void main(String[] args) {
            System.out.println("I'm Runner class");
        }
    }
}
```

24: Может ли внутренний класс быть раннер-классом?
Если да, то приведите пример, иначе поясните, почему нет.  
**Ответ.**  Нет, внутренний класс не может содержать статических методов. Соотвественно метод public static void main в нем реализовать нельзя.  

25: Может ли интерфейс иметь раннер-класс?
Если да, то приведите пример, иначе поясните, почему нет.   
**Ответ.** До Java-8 интерфейс не мог иметь статических методов, соотвественно метод public static void main в нем реализовать было невозможно.
Сейчас помощью Java-8 вы можете определить основной метод внутри интерфейса, т.к. любой класс, объявленный в интерфейсе - статический по умолчанию, т.е. может объявлять статические методы. Приведенный ниже код будет работать:  
```java
public interface TestInterfaces {
static class Runner {
    public static void main(String[] args){    
        System.out.println("I am a static main method inside Inteface !!");
    }
    }
}
```
