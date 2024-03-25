#!/usr/bin/env python
# coding: utf-8

# In[14]:


#import tensorflow as tf
import numpy as np
import os
import matplotlib.pyplot as plt
import cv2

datadirectory = "/Users/owner/downloads/dataset/training_set"
Catagories = ["cats", "dogs"]

for cata in Catagories:
    path = os.path.join(datadirectory, cata)
    #classification_num = Categories.index(cata)
    for img in os.listdir(path):
        img_arr = cv2.imread(os.path.join(path, img), cv2.IMREAD_GRAYSCALE)
        plt.imshow(img_arr, cmap="gray")
        plt.show()
        break
    break
        #new_arr = cv2.resize(img_arr, (img_sz,img_sz))
        #train_data.append([new_arr, classification_num])


# In[15]:


img_sz = 54
new_arr = cv2.resize(img_arr, (img_sz, img_sz))
plt.imshow(new_arr, cmap = "gray")
plt.show()


# In[16]:


#Creating data that will be inputed to neural network
train_data = []
def img_training_data():
    for cata in Catagories:
        path = os.path.join(datadirectory, cata)
        classification_num = Catagories.index(cata)
        for img in os.listdir(path):
            try:
                img_arr = cv2.imread(os.path.join(path, img), cv2.IMREAD_GRAYSCALE)
                new_arr = cv2.resize(img_arr, (img_sz, img_sz))
                train_data.append([new_arr, classification_num])
            except Exception as e:
                pass
            
img_training_data()


# In[17]:


print(len(train_data)) #prints length of data to see if data for both image categories are balanced


# In[18]:


import random
random.shuffle(train_data)


# In[19]:


for sample in train_data[:10]:
    print(sample[1])


# In[20]:


feature_setx = []
labels_y = []


# In[21]:


#appends features to a array using numpy
for features, labels in train_data:
    feature_setx.append(features)
    labels_y.append(labels)
    
#converting features into a numpy to pass into neural network model
feature_setx = np.array(feature_setx).reshape(-1, img_sz, img_sz, 1)
labels_y = np.array(labels_y)


# In[11]:


import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Dropout, Activation, Flatten, Conv2D, MaxPooling2D

feature_setx = feature_setx/255.0

model = Sequential()

#Layer 1
model.add(Conv2D(64, (2,2), input_shape = feature_setx.shape[1:]))
model.add(Activation("relu"))
#starts pooling
model.add(MaxPooling2D(pool_size=(2,2)))

#Layer 2
model.add(Conv2D(64, (2,2)))
model.add(Activation("relu"))
#starts pooling
model.add(MaxPooling2D(pool_size=(2,2)))

#Layer3
model.add(Flatten())
model.add(Dense(64))

#Output layer
model.add(Dense(1))
model.add(Activation("sigmoid"))

model.compile(loss="binary_crossentropy", optimizer="adam", metrics=['accuracy'])

model.fit(feature_setx, labels_y, batch_size = 42, epochs=15, validation_split=.10)


# In[ ]:




