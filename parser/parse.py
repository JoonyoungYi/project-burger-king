#encoding=UTF-8
from bs4 import BeautifulSoup
import requests
import csv, codecs, cStringIO
import sys
import traceback
import json

"""

"""
class Food:    

    def __init__(self, name_class, price, name, image_path):
        self.name_class = name_class
        self.price = price
        self.name = name
        self.image_path = 'https://mdelivery.burgerking.co.kr' + image_path

#https://mdelivery.burgerking.co.kr/product/getCategoryOptions

"""

"""
def requestFoodList(class_id, base_id, more_count):

    url = 'https://mdelivery.burgerking.co.kr/product/getMenuList'
    params = {'class_id': class_id, 'base_id': base_id, 'more_count': more_count}
    r = requests.post(url, params)
    r.encoding = 'utf-8'

    data = r.text
    jsonArray = json.loads(data)

    foods = []

    for jsonObject in jsonArray:
        name_class = jsonObject[u'class_nm']
        price = jsonObject[u'price_0']
        name = jsonObject[u'product_nm']
        image_path = jsonObject[u'image_path']
        product_id = jsonObject[u'product_id']
        class_nm = jsonObject[u'class_nm']

        food = Food(name_class, price, name, image_path)
        food.class_nm = class_nm
        food.product_id = product_id

        foods.append(food)

    return foods

"""

"""
def requestFoodDetail(food, product_id):

    url = 'https://mdelivery.burgerking.co.kr/product/getProductDetail'
    params = { 'product_id':product_id }
    r = requests.post(url, params)
    r.encoding = 'utf-8'

    data = r.text
    jsonObject = json.loads(data)[0]

    food.description = jsonObject[u'product_desc'].strip()
    food.calories = jsonObject[u'calories']

"""

"""
if __name__ == "__main__":

    foods = []
    foods.extend(requestFoodList('00001', '00025', '2'))
    foods.extend(requestFoodList('00002', '00020', '2'))
    foods.extend(requestFoodList('00003', '00004', '2'))
    foods.extend(requestFoodList('00003', '00005', '2'))
    foods.extend(requestFoodList('00003', '00006', '2'))
    foods.extend(requestFoodList('00004', '00007', '2'))
    foods.extend(requestFoodList('00004', '00008', '2'))
    foods.extend(requestFoodList('00004', '00009', '2'))
    foods.extend(requestFoodList('00005', '00010', '2'))
    foods.extend(requestFoodList('00006', '00012', '2'))

    for food in foods:        
        
        requestFoodDetail(food, food.product_id)

        print food.class_nm + '\t' + food.name + '\t' + food.description + '\t' + food.calories
