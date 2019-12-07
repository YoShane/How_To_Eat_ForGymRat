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

    public void showAll() {

        //排序
        for (int i = 0; i < this.allFood.size(); i++) {
            for (int j = 0; j < this.allFood.size() - 1; j++) {
                //比大小,if 前>後 換位
                if (this.allFood.get(j).getType().compareTo(this.allFood.get(j+1).getType())>0) {
                    this.allFood.add(j, this.allFood.get(j+1));
                    this.allFood.remove(j+2);
                }
            }
        }

        for (int i = 0; i < this.allFood.size(); i++) {
            protein -= allFood.get(i).getProtein() * quantity.get(i);
            fat -= allFood.get(i).getFat() * quantity.get(i);
            carbohydrate -= allFood.get(i).getCarbohydrate() * quantity.get(i);
            System.out.printf("%d. %s(%s) x %d  / %s / 剩餘 %s\n", i + 1, allFood.get(i).getName(), allFood.get(i).getUnit(),
                    quantity.get(i), Food.formaterForContent(allFood.get(i).getProtein(), allFood.get(i).getFat(), allFood.get(i).getCarbohydrate(), quantity.get(i)),
                    Food.formaterForContent(protein, fat, carbohydrate, 1));
        }
    }

}
