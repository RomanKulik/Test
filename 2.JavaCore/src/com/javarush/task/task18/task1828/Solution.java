package com.javarush.task.task18.task1828;

/* 
Прайсы 2
Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD

Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id

Значения параметров:
где id - 8 символов
productName - название товара, 30 символов
price - цена, 8 символов
quantity - количество, 4 символа
-u - обновляет данные товара с заданным id
-d - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234


Требования:
1. Программа должна считать имя файла для операций CrUD с консоли.
2. При запуске программы без параметров список товаров должен остаться неизменным.
3. При запуске программы с параметрами "-u id productName price quantity" должна обновится информация о товаре в файле.
4. При запуске программы с параметрами "-d id" строка товара с заданным id должна быть удалена из файла.
5. Созданные для файлов потоки должны быть закрыты.
*/

import java.io.*;

public class Solution {
    private static String fileName = "d:/file.txt";

    public static void main(String[] args) throws ProductException {
        //ProductList.testProductList();
       if (args.length == 0 || !setFileName(args)) return;
       //fileName = ProductList.createTestFile();
        ProductList pl = new ProductList(fileName);
        switch (args[0]) {
            case "-c":
                // -c "Майка" 1 33
                    pl.put(pl.getNextID(), new Product(String.valueOf(pl.getNextID()),
                            args[1], args[2], args[3]));
                    pl.updateFile();
                break;
            case "-u":
                // -u 19847986 "Шорты шерстяные очень теплые и мягкие летние" 1110.22 33
                pl.update(new Product(args[1], args[2], args[3], args[4]));
                pl.updateFile();
                break;
            case "-d":
                // -d 19847986
                pl.remove(args[1]);
                pl.updateFile();
                break;
        }
    }

    private static boolean setFileName(String[] args) {
        try {
            fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}

/*public class Solution {
    public static void main(String[] args) {

    }
}*/
