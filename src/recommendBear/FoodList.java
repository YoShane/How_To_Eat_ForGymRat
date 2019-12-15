/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recommendBear;

import java.util.ArrayList;

/**
 *
 * @author shane
 */
public class FoodList {

    private double protein, fat, carbohydrate;
    private ArrayList<Food> allFood = new ArrayList<Food>();
    private ArrayList<Integer> quantity = new ArrayList<Integer>();

    public void initList() {
        allFood.clear();
        quantity.clear();
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public boolean add_food(Food food, int quantity) {
        boolean isAdd = true;
        int recordNum = 0;
        for (int i = 0; i < this.allFood.size(); i++) {
            if (this.allFood.get(i).getId() == food.getId()) {
                isAdd = false;
                recordNum = i;
                break;
            }
        }

        if (isAdd) {
            this.allFood.add(food);
            this.quantity.add(quantity);
        } else {
            this.quantity.set(recordNum, this.quantity.get(recordNum) + quantity);
        }
        return isAdd;
    }

    public void remove_lastOne() {
        int rmN = this.allFood.size() - 1;
        this.allFood.remove(rmN);
        this.quantity.remove(rmN);
    }

    public void adjustList() {

        int fistRiceFood = -1;
        boolean findIt = false;
        double rProtein = 0;
        double rFat = 0;
        double rCarbohydrate = 0;

        for (int i = 0; i < this.allFood.size(); i++) {
            //排序
            for (int j = 0; j < this.allFood.size() - 1; j++) {
                //比大小,if 前>後 換位
                if (this.allFood.get(j).getType().compareTo(this.allFood.get(j + 1).getType()) > 0) {
                    this.allFood.add(j, this.allFood.get(j + 1));
                    this.allFood.remove(j + 2);
                }
            }
        }

        for (int i = 0; i < this.allFood.size(); i++) {
            if (!findIt && this.allFood.get(i).getType().equals("全榖雜糧類")) {
                fistRiceFood = i;
                findIt = true;
            } else if (this.allFood.get(i).getType().equals("全榖雜糧類")) {
                rProtein += this.allFood.get(i).getProtein() * this.quantity.get(i);
                rFat += this.allFood.get(i).getFat() * this.quantity.get(i);
                rCarbohydrate += this.allFood.get(i).getCarbohydrate() * this.quantity.get(i);
            }
        }

        //取完第一個數量
        while (Food.checkPut(this.allFood.get(fistRiceFood), rProtein, rFat, rCarbohydrate)) { //如果還夠取
            add_food(this.allFood.get(fistRiceFood), this.allFood.get(fistRiceFood).getLimit()); //放食物與數量
            rProtein -= (this.allFood.get(fistRiceFood).getProtein() * this.allFood.get(fistRiceFood).getLimit());
            rFat -= (this.allFood.get(fistRiceFood).getFat() * this.allFood.get(fistRiceFood).getLimit());
            rCarbohydrate -= (this.allFood.get(fistRiceFood).getCarbohydrate() * this.allFood.get(fistRiceFood).getLimit());
        }

        for (int i = 0; i < this.allFood.size(); i++) { //刪掉其他雜糧
            if (this.allFood.get(i).getType().equals("全榖雜糧類") && i != fistRiceFood) {
                this.allFood.remove(i);
                this.quantity.remove(i);
            }
        }

    }

    public String showAll() {
        String result = new String();
        for (int i = 0; i < this.allFood.size(); i++) {
            protein -= allFood.get(i).getProtein() * quantity.get(i);
            fat -= allFood.get(i).getFat() * quantity.get(i);
            carbohydrate -= allFood.get(i).getCarbohydrate() * quantity.get(i);
            result +=  String.format("%s-- %d. %s(%s) x %d  /  共%d克  / %s / 剩餘 %s\n", allFood.get(i).getType(), i + 1, allFood.get(i).getName(), allFood.get(i).getUnit(),
                    quantity.get(i), allFood.get(i).getWeight() * quantity.get(i), Food.formaterForContent(allFood.get(i).getProtein(), allFood.get(i).getFat(), allFood.get(i).getCarbohydrate(), quantity.get(i)),
                    Food.formaterForContent(protein, fat, carbohydrate, 1));
        }
        result+= "-----------------------------------------------------------------------------------------------------\n";
        return result;
    }

}
