1: Что такое лямбда-выражение?  
**Ответ.**  Лямбда-выражение или просто лямбда в Java — упрощённая запись анонимного класса, реализующего функциональный интерфейс.  
Одна проблема с анонимными классами заключается в том, что если реализация вашего анонимного класса очень проста, например интерфейс, содержащий только один метод, то синтаксис анонимных классов может показаться громоздким и неясным. В этих случаях вы обычно пытаетесь передать функциональность в качестве аргумента другому методу, например, какое действие должно быть выполнено, когда кто-то нажимает кнопку. Лямбда-выражения позволяют вам сделать это, рассматривая функциональность как аргумент метода или код как данные.  
**Источник.**  https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

2: Из каких элементов состоит объявление лямбда-выражения?  
**Ответ.**  Лямбда-выражение состоит из следующего:  
а) Разделенный запятыми список формальных параметров, заключенный в круглые скобки.  
Метод `CheckPerson.test` содержит один параметр, `p` который представляет экземпляр `Person` класса.
Примечание. Тип данных параметров в лямбда-выражении можно не указывать. Кроме того, вы можете
опустить круглые скобки, если имеется только один параметр. Например, следующее лямбда-выражение
также допустимо:
```java
p -> p.getGender() == Person.Sex.MALE
    && p.getAge() >= 18
    && p.getAge() <= 25
```
б) Жетон стрелки `->`
в) Тело, состоящее из одного выражения или блока операторов. В этом примере используется следующее выражение:
```java
p.getGender() == Person.Sex.MALE
    && p.getAge() >= 18
    && p.getAge() <= 25
```
Если вы укажете одно выражение, среда выполнения Java вычислит выражение и затем вернет его значение. В качестве альтернативы вы можете использовать оператор return:
```java
р -> {
    вернуть p.getGender() == Person.Sex.MALE
        && p.getAge() >= 18
        && p.getAge() <= 25;
}
```
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

3: При объявлении лямбда-выражения какие структуры можно опустить?  
**Ответ.**  Если у лямбда-выражения всего один аргумент, и для него не требуется объявление типа или var, то круглые скобки можно опустить. В остальных случаях, в том числе если лямбда не принимает никаких аргументов, скобки нельзя опустить.
Аналогичная ситуация и с телом лямбда-выражений: если оно состоит только из одной строки, то фигурные скобки, точку с запятой (;) и директиву return можно тоже опустить.
В качестве тела лямбда-выражения может использоваться ссылка на метод.  
**Источник.** https://alexkosarev.name/2019/03/11/lambdas-in-java/

4: Что такое поток и конвейер в контексте лямбда-выражения?  
**Ответ.** Поток — это последовательность элементов. В отличие от коллекции, это не структура данных,
в которой хранятся элементы. Вместо этого поток переносит значения из источника по конвейеру.
В этом примере создается поток из коллекции `roster` путем вызова метода `stream`.  
Конвейер — это последовательность агрегатных операций.  
**Источник:** https://docs.oracle.com/javase/tutorial/collections/streams/index.html#pipelines

5: Какие компоненты содержит конвейер?  
**Ответ.**  
а) Источник: это может быть коллекция, массив, функция-генератор или канал ввода-вывода.
В этом примере источником является коллекция `roster`.  
б) Ноль или более промежуточных операций . Промежуточная операция, такая как `filter`, создает новый поток.  
в) Поток — это последовательность элементов. В отличие от коллекции, это не структура данных,
в которой хранятся элементы. Вместо этого поток переносит значения из источника по конвейеру.
В этом примере создается поток из коллекции `roster` путем вызова метода `stream`.
Операция `filter` возвращает новый поток, содержащий элементы, соответствующие его предикату
(параметр этой операции). В этом примере предикатом является лямбда-выражение
`e -> e.getGender() == Person.Sex.MALE`. Он возвращает логическое значение true, если `gender` поле
объекта `e` имеет значение `Person.Sex.MALE`. Следовательно, `filter` операция в этом примере возвращает
поток, содержащий всех членов мужского пола в коллекции `roster`.  
г) Терминальная операция . Операция терминала, такая как `forEach`, создает результат, не являющийся
потоковым, например, примитивное значение (например, двойное значение), набор или, в случае `forEach`,
вообще не имеет значения. В этом примере параметром `forEach` операции является лямбда-выражение
`e -> System.out.println(e.getName())`, которое вызывает метод` getName` для объекта e.
(Среда выполнения Java и компилятор делают вывод, что тип объекта `e — Person`.)  
**Источник.** https://docs.oracle.com/javase/tutorial/collections/streams/index.html#pipelines


6: Что такое агрегатные операции? Приведите примеры агрегатных операций.  
**Ответ.** Агрегатные операции часто используются в сочетании с лямбда-выражениями, чтобы сделать
программирование более выразительным, используя меньше строк кода.  
Операции `filter`, `map` и `forEach` являются агрегатными операциями . Агрегированные операции обрабатывают
элементы из потока, а не напрямую из коллекции (именно поэтому в этом примере вызывается первый метод `stream`).
В следующем примере агрегатные операции используются для печати адресов электронной почты тех участников,
содержащихся в коллекции `roster`, которые имеют право на выборочное обслуживание:
```java
roster.stream().filter(
        p -> p.getGender() == Person.Sex.MALE
            && p.getAge() >= 18
            && p.getAge() <= 25)
        .map(p -> p.getEmailAddress())
        .forEach(email -> System.out.println(email));
```
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach9

7: Какие различия между агрегатными операциями и итераторами?  
**Ответ.**  Агрегированные операции, такие как `forEach`, похожи на итераторы.
Однако они имеют несколько принципиальных отличий:
а) Они используют внутреннюю итерацию : агрегатные операции не содержат метода, подобного `next`
указанию им обработать следующий элемент коллекции. При внутреннем делегировании ваше приложение
определяет, какую коллекцию оно итерирует, но `JDK` определяет, как итерировать коллекцию.
С внешней итерацией, ваше приложение определяет и то, какую коллекцию оно повторяет, и то, как
оно это делает. Однако внешняя итерация может выполнять итерацию только по элементам коллекции
последовательно. Внутренняя итерация не имеет этого ограничения. Он может легче использовать
преимущества параллельных вычислений, которые включают разделение проблемы на подзадачи,
одновременное решение этих проблем и последующее объединение результатов решений подзадач.

б) Они обрабатывают элементы из потока: агрегатные операции обрабатывают элементы из потока,
а не непосредственно из коллекции. Следовательно, их также называют потоковыми операциями.

в) Они поддерживают поведение в качестве параметров : вы можете указать лямбда-выражения в качестве
параметров для большинства агрегатных операций. Это позволяет настроить поведение конкретной агрегатной операции.  
**Источник.** https://docs.oracle.com/javase/tutorial/collections/streams/index.html#pipelines  

8: Какие имеются ограничения на локальные переменные, которые используются в лямбда-выражениях?  
**Ответ.** подобно локальным и анонимным классам, лямбда-выражение может обращаться только к
локальным переменным и параметрам включающего блока, которые являются окончательными или
фактически окончательными.  
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach9


9: Что такое целевой тип лямбда-выражения и как компилятор определяет целевой тип?  
**Ответ.** Когда среда выполнения `Java` вызывает метод `printPersons`, она ожидает тип данных `CheckPerson`,
поэтому лямбда-выражение имеет этот тип. Однако когда среда выполнения `Java` вызывает метод
`printPersonsWithPredicate`, она ожидает тип данных `Predicate<Person>`, поэтому лямбда-выражение имеет
этот тип. Тип данных, ожидаемый этими методами, называется целевым типом. Чтобы определить тип
лямбда-выражения, компилятор `Java` использует целевой тип контекста или ситуации, в которой было
найдено лямбда-выражение. Из этого следует, что вы можете использовать лямбда-выражения только в
ситуациях, когда компилятор `Java` может определить целевой тип:  
а) Объявления переменных  
б) Задания  
в) Операторы возврата  
г) Инициализаторы массива  
д) Аргументы метода или конструктора  
е) Тела лямбда-выражений  
ж) Условные выражения, ?:  
з) Приведение выражений   
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#target-types-and-method-arguments

10: В каких ситуациях может использоваться лямбда-выражение?  
**Ответ.** Одна проблема с анонимными классами заключается в том, что если реализация вашего
анонимного класса очень проста, например интерфейс, содержащий только один метод, то синтаксис
анонимных классов может показаться громоздким и неясным. В этих случаях вы обычно пытаетесь передать
функциональность в качестве аргумента другому методу, например, какое действие должно быть выполнено,
когда кто-то нажимает кнопку. Лямбда-выражения позволяют вам сделать это, рассматривая
функциональность как аргумент метода или код как данные.  Лямбда-выражения позволяют более компактно
выражать экземпляры классов с одним методом.  
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html

11: Могут ли лямбда-выражения ссылаться на другие существующие методы? Если да - приведите пример.  
**Ответ.**  Иногда лямбда-выражение не делает ничего, кроме вызова существующего метода.
В этих случаях часто удобнее ссылаться на существующий метод по имени. Ссылки на методы позволяют
это сделать; они представляют собой компактные, легко читаемые лямбда-выражения для методов,
у которых уже есть имя.
```java
public class Person {

    // ...
    
    LocalDate birthday;
    
    public int getAge() {
        // ...
    }
    
    public LocalDate getBirthday() {
        return birthday;
    }   

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }
    
    // ...
} 
Arrays.sort(rosterAsArray, Person::compareByAge);
```
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html


12: Укажите виды ссылок на методы в контексте лямбда-выражений и приведите соответствующие пример.  
**Ответ.** Существует четыре вида ссылок на методы:
а) Ссылка на статический метод  
Пример:
```java
Person::compareByAge
MethodReferencesExamples::appendStrings
```

б) Ссылка на метод экземпляра конкретного объекта  
Пример:
```java
myComparisonProvider::compareByName
myApp::appendStrings2
``` 

в) Ссылка на метод экземпляра произвольного объекта определенного типа  
Пример:
```java
String::compareToIgnoreCase
String::concat
```

г) Ссылка на конструктор  
Пример:
```java
HashSet::new 
```
**Источник.** https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html


13: Что такое операции сокращения в контексте лямбда-выражений?  
**Ответ.** `JDK` также содержит операции сокращения, которые возвращают набор вместо одного значения.
Многие операции редукции выполняют определенную задачу, например нахождение среднего значения или
группировку элементов по категориям. Однако `JDK` предоставляет вам операции сокращения общего
назначения `reduce` и `collect`.  
**Источник:** https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html


14: Чем метод `reduce` отличается от метода `collect` в контексте лямбда-выражений?  
**Ответ.** В отличие от `reduce` метода, который всегда создает новое значение при обработке
элемента, `collect` метод изменяет или видоизменяет существующее значение.  
**Источник:** https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html


15: Укажите, какие преимущества дает использование класса `Optional`?  
**Ответ.** Главная же цель `Optional` — замена `null`-значений, благодаря чему должна повышаться
безопасность и читаемость кода.  
**Источник:** https://alexkosarev.name/2019/03/15/optional-in-java/


16: Выполните вывод каждого элемента коллекции `collect` с помощью метода `forEach ()`, применяя:
итератор  
поток  
**Ответ.** 
```java
Collection<Integer> collect = ...;
for(Integer n: collect) {     //1
System.out.println(n);
}   
collect.forEach(System.out::println);      //2
```


17: Выполните вывод каждого элемента `Map collect` с помощью `java 8`.  
**Ответ.** 
```java
Map<Integer,String> collect = new HashMap<>();
collect.put(1,"ww");
collect.put(2,"333");
collect.put(3,"fwfw");
collect.forEach((K,V) -> System.out.println(K + " " + V));
```


18: Допишите сортировку коллекции users по имени с помощью метода `getName()` и вывод всех элементов коллекции в консоль (класс `User` приводить не обязательно).  
```java
public class Main {
public static void main(String[] args) {
List<User> users = new ArrayList<>();
users.add(new User("Nick", "Boll"));
users.add(new User("Jan", "Nicky"));
users.add(new User("Bot", "Smart"));
...
}
}
```
**Ответ.**
```java
List<User> users = new ArrayList<>();
users.add(new User("Nick", "Boll"));
users.add(new User("Jan", "Nicky"));
users.add(new User("Bot", "Smart"));
users.stream()
        .sorted(Comparator.comparing(User::getName))
        .forEach(System.out::println);
``` 


19: Допишите код, чтобы вывести только четные элементы коллекции, используя метод `filter()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
…
}
}
```
**Ответ.** 
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    numbers.stream().
            filter(f -> f % 2 ==0).
            forEach(System.out::println);
}
}
```

20: Допишите код, чтобы вывести количество элементов коллекции, длина которых больше 4, используя методы `filter()` и `count()`.  
```java
public class Main {
public static void main(String[] args) {
List<String> names = Arrays.asList("John", "Jan", "Tirion", "Marry", "Nikolas");
…
}
}
```
**Ответ.** 
```java
public class Main {
    public static void main(String[] args) {
        System.out.println(names.stream()
                .filter(str -> str.length() > 4)
                .count());
    }
}
```


21: Допишите код, чтобы вывести каждый элемент коллекции, умножив его на 2, используя метод `map()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 3, 5, 7);
…
}
}
```
**Ответ.**  
```java
public class Main {
    public static void main(String[] args) {
        numbers.stream().map(e -> e * 2).forEach(System.out::println);
    }
}
```

22: Создайте новую коллекцию `ArrayList` и выведите в консоль список четных чисел из коллекции `numbers` с использованием методов `filter()` и `collect()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
List<Integer> evenNumbers = ...
…
}
}
```
**Ответ.**  
```java
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = numbers.stream()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(evenNumbers);
    }
}
```

23: Создайте новую коллекцию `LinkedList` (имплементация `Queue`) и выведите в консоль НЕ пустые строки из коллекции `ArrayList names` с использованием методов `filter()` и `collect()`.  

```java
public class Main {
public static void main(String[] args) {
List<String> names = Arrays.asList("Jaime", "Daenerys", "", "Tyrion", "");
Queue<String> queue = ….
…
}
}
```
**Ответ.**  
```java
public class Main {
public static void main(String[] args) {
    List<String> names = Arrays.asList("Jaime", "Daenerys", "", "Tyrion", "");
    Queue<String> queue = names.stream()
            .filter(str -> str.length() != 0)
            .collect(Collectors.toCollection(LinkedList::new));
    System.out.println(queue);
}
}
```

24: Выведите имена домашних животных, объединив их в новую коллекцию `List<String>` `petNames` из коллекции их хозяев `humans`, у которых имена домашних животных являются полями класса (в виде отдельных коллекций), используя метод `flatMap()`.  

```java
public class Main {
public static void main(String[] args) {
List<Human> humans = asList(
new Human("Sam", asList("Buddy", "Lucy")),
new Human("Bob", asList("Frankie", "Rosie")),
new Human("Marta", asList("Simba", "Tilly")));
List<String> petNames = ...
…
}
}
```
**Ответ.** 
```java
public class Main {
    public static void main(String[] args) {
        List<Human> humans = asList(
                new Human("Sam", asList("Buddy", "Lucy")),
                new Human("Bob", asList("Frankie", "Rosie")),
                new Human("Marta", asList("Simba", "Tilly")));
        List<String> petNames = humans
                .stream()
                .flatMap(h -> h.getPetNames().stream())
                .collect(Collectors.toList());
        petNames.forEach(System.out::println);
    }
}
```

25: Найдите и выведите первое по счету число, которое больше 10, используя методы `filter()` и `findFirst()`.  

```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 5, 8, 10, 12, 15);
Optional<Integer> first = ...
…
}
}
```
**Ответ.**  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 5, 8, 10, 12, 15);
Optional<Integer> first = numbers.stream()
        .filter(e -> e > 10)
        .findFirst();
    System.out.println(first);
}
}
```

26: Найдите и выведите первую попавшуюся фразу (с учетом возможного многопоточного процесса), которая содержит фрагмент `Java`, используя методы `filter()` и `findAny()`.  

```java
public class Main {
public static void main(String[] args) {
List<String> strings = Arrays.asList("Java is the best", "Java 8", "Java 9", "Jacoco");
Optional<String> java = …
...
}
}
```
**Ответ.** 
```java
public class Main {
public static void main(String[] args) {
    List<String> strings = Arrays.asList("Java is the best", "Java 8", "Java 9", "Jacoco");
    Optional<String> java = strings.stream().filter(str -> str.contains("Java")).findAny();
    System.out.println(java);
}
}
```

27: Выведите `boolean`, имеется ли в коллекции хотя бы одно четное значение, используя метод `anyMatch()`.  
```java
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 4, 5, 7);
boolean match = ...
...
}
}
```
**Ответ.** 
```java
public class Main {
public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 4, 5, 7);
    boolean match = numbers.stream().anyMatch(n -> n % 2 == 0);
    System.out.println(match);
}
}
```
29: Выведите boolean, являются ли все числа коллекции положительным, используя метод allMatch().
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
boolean match = ...
...
}
}


30: Выведите boolean, НЕ являются ли все числа коллекции четными, используя метод noneMatch().
public class Main {
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9);
boolean match = ...
...
}
}


31: Какие из ниже приведенных ламбда-выражений не скомпилируется и почему?
(int x, int y) -> x+y  
(x, y) -> x+y          
(x, int y) -> x+y   
(x, final y) -> x+y
int x -> x;
x -> y -> x + y;
x -> (final int y) -> y + x;
x -> x -> 5;


32: Скомпилируется ли следующий код и почему?
for (byte b : "Java".getBytes()) {
foo(() -> b);
}


33: Дана матрица 3х3 используя Java 8 преобразуйте ее в одномерный массив.
int[][] matrix = {   {1, 2, 3}
, {4, 5, 6}
, {7, 8, 9}};
int[] array = ….


34: Даны классы:
class BlogPost {
String title;
String author;
BlogPostType type;
int likes;
}
enum BlogPostType {
NEWS,
REVIEW,
GUIDE
}
List<BlogPost> posts = Arrays.asList( ... );

Определите:
Все уникальные статьи относящиеся к каждому типу статей.
Для каждого типа статьи определите статью с максимальным количеством лайков.
Все статьи относящиеся к каждому типу статей, список статей должен представлять собой строку формата: “Post titles: [title1, title2, …..] “


35: Приведите два способа получения последнего элемента в потоке, в чем особенности вычисления этого значения в потоках.


36: Дан код, можно ли его как-то отрефакторить? Если да, то сделайте это.
Подсказка:
Добавьте в список элемент с автором, которые уже есть в списке и проверьте приложение
books.add(new Book("Java: A Beginner's Guide", "Herbert Schildt"));
class Book {
private String name;
private String author;

// getters and setters
…
}

List<Book> books = new ArrayList<>();

books.add(new Book("Effective Java", "Joshua Bloch"));
books.add(new Book("Thinking in Java", "Bruce Eckel"));
books.add(new Book("Java: The Complete Reference", "Herbert Schildt"));

Map<String, String> bookMap = books.stream().collect(
Collectors.toMap(Book::getAuthor, Book::getName));
bookMap.forEach((author, book) ->
System.out.println("Author: " + author + " Books: " + book));


37: Дан код
class Employee {
Integer employeeId;
String employeeName;

    // getters and setters
}

class Department {
Integer employeeId;
String department;

    // getters and setters
}

public class Main {
public static void main(String[] args) {
List<Employee> employees = new ArrayList<>();
List<Department> departments = new ArrayList<>();

        populate(employees, departments);

       List<Employee> salesEmpoyees = ...
    }
}

Замените многоточие, чтобы определить сотрудников находящихся в отделе “sales”


38: Дан код
class Tuple<T1, T2> {
private T1 item1;
private T2 item2;
// getters and setters
}
List<String> names = new ArrayList<>(Arrays.asList("John", "Jane", "Jack", "Dennis"));
List<Integer> ages = new ArrayList<>(Arrays.asList(24, 25, 27));
List<Tuple<String, Integer>> namesAndAges = …

Выполните операцию ‘zip’ для коллекций ages и names.
Zip: операция «zip» немного отличается от стандартной «concat» или «merge». В то время как операции «concat» или «merge» просто добавят новую коллекцию в конец существующей коллекции, операция «zip» возьмет элемент из каждой коллекции и объединит их.
Например, в результате выполнения этого задания должна получиться коллекция:
[John;24, Jane;25, Jack;27]


39: Дан код, замените  {code} и {type} так, чтобы получить нужные результаты
Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");
// Удалить все дубликаты
List<String> unique= strings.stream().{code}
// напечатает unique= [a1, b2, c3]
System.out.println("unique = " + unique);
// Объединить все элементы в одну строку через разделитель : и обернуть тегами <b> ... </b>
String join = strings.stream().collect({code});
// напечатает <b> a1 : b2 : c3 : a1 </b>
System.out.println("join = " + join);
// Преобразовать в map, сгруппировав по первому символу строки
Map<String, List<String>> groups = strings.stream().collect({code});
// напечатает groups = {a=[a1, a1], b=[b2], c=[c3]}
System.out.println("groups = " + groups);
// Преобразовать в map, сгруппировав по первому символу строки и в качестве значения взять второй символ, если ключ повторяется, то значения объединить через “:”
Map<String, String> groupJoin = strings.stream()
.collect(Collectors.groupingBy({code}));
// напечатает groupJoin = groupJoin = {a=1:1, b=2, c=3}
System.out.println("groupJoin = " + groupJoin);

Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);
// Получить сумму нечетных чисел
{type} sumOdd = numbers.stream().collect({code});
// напечатает sumEven = 4
System.out.println("sumOdd = " + sumOdd);
// Вычесть из каждого элемента 1 и получить среднее
double average = numbers.stream().collect({code});
// напечатает average = 1.5
System.out.println("average = " + average);
// Прибавить к числам 3 и получить статистику: количество элементов, их сумму, макс и мин. значения, а также их среднее.
{type} statistics = numbers.stream().collect({code});
// напечатает statistics = … {count=4, sum=22, min=4, average=5.500000, max=7}
System.out.println("statistics = " + statistics);
// Разделить числа на четные и нечетные
Map<Boolean, List<Integer>> parts = numbers.stream().collect({code});
// напечатает parts = {false=[1, 3], true=[2, 4]}
System.out.println("parts = " + parts);



40: Дан поток, определите количество вхождений каждого из символов, составляющих поток.
Stream<String> words = Stream.of("Java", "Magazine", "is", "the", "best");


41: Дан код, как он будет выглядеть если modem обернуть в Optional?
boolean isInRange = false;
if (modem != null && modem.getPrice() != null
&& (modem.getPrice() >= 10
&& modem.getPrice() <= 15)) {
isInRange = true;
}
return isInRange;


42: Дан код, замените {code}, чтобы получить первый объект, которые не null, если такого нет вернуть “default’
private Optional<String> getEmpty() {
return Optional.empty();
}

private Optional<String> getHello() {
return Optional.of("hello");
}

private Optional<String> getBye() {
return Optional.of("bye");
}
String firstNonNull = Stream.of(getEmpty(), getHello(), getBye()).{code};
