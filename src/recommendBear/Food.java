/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendBear;

/**
 *
 * @author shane
 */
public class Food {

    private int id;
    private String name;
    private String unit;
    private int weight;
    private double protein;
    private double fat;
    private double carbohydrate;
    private int limit;
    private String type;

    public Food() {
    }

    public Food(int id, String name, String unit, int weight, double protein, double fat, double carbohydrate, int limit, String type) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.weight = weight;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.limit = limit;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getWeight() {
        return weight;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public int getLimit() {
        return limit;
    }

    public String getType() {
        return type;
    }

    //專屬給食物處理的函式
    public static boolean checkPut(Food food, double protein, double fat, double carbohydrate) {
        boolean check = true;
        if (protein - food.getProtein() < 0) {
            check = false;
        }
        if (fat - food.getFat() < 0) {
            check = false;
        }
        if (carbohydrate - food.getCarbohydrate() < 0) {
            check = false;
        }
        return check;
    }

    public static String formaterForContent(double protein, double fat, double carbohydrate, int limit) {
        String result = "";
        String proW = "";
        String fatW = "";
        String carW = "";

        if (protein != 0.0) {
            double tmp;
            if (protein * limit > 0) {
                tmp = protein * limit;
            } else {
                tmp = 0;
            }
            proW = String.format("蛋%.0f克、", tmp);
        }
        if (protein != 0.0) {
            double tmp;
            if (fat * limit > 0) {
                tmp = fat * limit;
            } else {
                tmp = 0;
            }
            fatW = String.format("脂%.0f克、", tmp);
        }
        if (carbohydrate != 0.0) {
            double tmp;
            if (carbohydrate * limit > 0) {
                tmp = carbohydrate * limit;
            } else {
                tmp = 0;
            }
            carW = String.format("醣%.0f克、", tmp);
        }

        result = proW + fatW + carW;
        if (!result.equals("")) {
            result = result.substring(0, result.length() - 1);
        } else {
            result = "請輸入數值";
        }

        return result;
    }

}
