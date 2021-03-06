public class Task_1 implements Task_1_base {
    @Override
    public int subtask_1_if(int first, int second, int third) {
        // Вычислить и вернуть минимальный из трех полученных аргументов (first, second, third)
        // ------------------------------------------------------------------------------------
        int min = first;
        if (min > second)
            min = second;
        if (min > third)
            min = third;
        return min;
    }
    @Override
    public boolean subtask_2_if(int year) {
        // Проверить, является ли год, переданный в параметре year, високосным.
        // Високосный год - это год, кратный четырем, но не кратный 100, либо кратный 400
        // ------------------------------------------------------------------------------------
        if (year < 0)
        {
            return false;
        }
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }
    @Override
    public int subtask_3_if(double x, double y, double left_up_x, double left_up_y, double width, double height) {
        // Проверить, лежит ли точка с координатами (x, y) внутри невырожденного квадрата
        // со сторонами, параллельными осям координат, левый верхний угол которого имеет
        // координаты (left_up_x, left_up_y), ширина которого width, высота height.
        // Точка на границе считается не лежащей внутри.
        // В качестве результата вернуть:
        // 0 - не лежит
        // 1 - лежит
        // 2 - аргументы функции заданы некорректно
        // Допустимой погрешностью при сравнении переменных типа double считать 0.000001
        // ------------------------------------------------------------------------------------
        if (width < 0.000001 || height < 0.000001)
            return 2;
        double right_x = left_up_x + width;
        double bottom_y = left_up_y - height;
        if(right_x - x < 0.000001 || x - left_up_x < 0.000001 )
            return 0;
        if(y - bottom_y < 0.000001 || left_up_y - y < 0.000001 )
            return 0;
        return 1;
    }
    @Override
    public int subtask_4_if(double x0, double y0, double k, double b) {
        // Проверить, как расположена точка с координатами (x0, y0)
        // относительно прямой y = kx + b
        // В качестве результата вернуть:
        // 0 - лежит выше прямой
        // 1 - лежит ниже прямой
        // 2 - лежит на прямой
        // Допустимой погрешностью при сравнении переменных типа double считать 0.000001
        // ------------------------------------------------------------------------------------
        double x0_on_line_y = k*x0 + b;
        if (Math.abs(x0_on_line_y - y0) < 0.000001)
            return 2;
        if (x0_on_line_y - y0 < 0)
            return 0;
        if (x0_on_line_y - y0 > 0)
            return 1;
        return -1; // Замените данный оператор кодом, решающим поставленную задачу.
    }
    @Override
    public String subtask_5_switch(int day_od_week) {
        // По номеру дня недели day_od_week вернуть его название на русском языке, записанное
        // с большой буквы. Дни едели отсчитываются с единицы. Если номер задан некорректно,
        // вернуть строку "Ошибка"
        // ------------------------------------------------------------------------------------
        switch (day_od_week)
        {
            case 1:
                return "Понедельник";

            case 2:
                return "Вторник";

            case 3:
                return "Среда";

            case 4:
                return "Четверг";

            case 5:
                return "Пятница";

            case 6:
                return "Суббота";

            case 7:
                return "Воскресенье";

            default:
                return "Ошибка";
        }
    }
    @Override
    public String subtask_6_switch(int direction) {
        // По заданному направлению direction вернуть его название:
        // 1 - север
        // 2 - юг
        // 3 - запад
        // 4 - восток
        // Во всех остальных случаях вернуть пустую строку
        // ------------------------------------------------------------------------------------
        switch (direction)
        {
            case 1:
                return "север";

            case 2:
                return "юг";

            case 3:
                return "запад";

            case 4:
                return "восток";

            default:
                return "";
        }
    }
    @Override
    public int subtask_7_if(double vx, double vy, double vz, double speed, double time, double wall) {
        // Проверить, достигнет ли снаряд, летяший из точки (0, 0, 0)
        // в направлении (vx, vy, vz) со скоростью speed стены, параллельной плоскости OYZ,
        // имеющей координату x равной wall, за время time
        // 0 - не достигнет
        // 1 - достигнет
        // 2 - аргументы функции заданы некорректно
        // Допустимой погрешностью при сравнении переменных типа double считать 0.000001
        // ------------------------------------------------------------------------------------
        if (time < 0.000001 || speed < 0.000001)
            return 2;
        if (Math.abs(vx) < 0.000001 && Math.abs(wall) < 0.000001)
            return 1;
        if (Math.abs(vx) < 0.000001)
            return 0;
        double len = Math.sqrt((wall * wall) + (wall*vy * wall*vy) + (wall*vz * wall*vz));   // длина
        if (((len / speed) - time) < 0.000001)
            return 1;
        return 0;
    }
    @Override
    public int subtask_8_if(double k1, double b1, double k2, double b2) {
        // Проверить, как друг относительно друга располагаются прямые y = k1*x + b1 и
        // y = k2*x + b2. Вернуть
        // 1 - если параллельны | k1 == k2
        // 2 - если пересекаются | ни 3 и ни 4
        // 3 - если совпадают | k1 == k2 && b1 == b2
        // Допустимой погрешностью при сравнении переменных типа double считать 0.000001
        // -----------------------------------------------------------------------------------
        if (Math.abs(k1 - k2) < 0.000001)
        {
            if (Math.abs(b1 - b2) < 0.000001)
                return 3;
            return 1;
        }
        return 2; // Замените данный оператор кодом, решающим поставленную задачу.
    }
}
