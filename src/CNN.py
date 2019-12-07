# -*- coding: utf-8 -*-
"""
Created on Fri Dec  6 18:25:57 2019

@author: Fernando
"""

from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential, load_model
from tensorflow.keras.layers import Dense, Conv2D, Flatten, MaxPooling2D, Dropout
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.preprocessing.image import ImageDataGenerator
import Augmentor as aug
import numpy as np
import pickle as pkl

import time
import matplotlib.pyplot as plt

batch_size = 128
num_classes = 10
epochs = 20


(train_images, train_labels), (test_images, test_labels) = mnist.load_data()

train_images = train_images.reshape((60000, 28,28,1))
test_images = test_images.reshape((10000, 28,28,1))                                  

train_labels = to_categorical(train_labels)
test_labels = to_categorical(test_labels)

def elastic_augmentor(train_images, train_labels):
    pipe = aug.Pipeline()
    pipe.random_distortion(probability = 1, grid_width = 3, grid_height = 3, magnitude = 3)
    g = pipe.keras_generator_from_array(train_images, train_labels, batch_size=40000)

    X, y = next(g)
    train_images = np.concatenate((train_images,X), axis=0)
    train_labels = np.concatenate((train_labels,y), axis=0)
    return [train_images, train_labels]

# =============================================================================
def aument_dataset(train_images, train_labels):
    image_generator = ImageDataGenerator(
                rotation_range=10,
                zoom_range = 0.05, 
                width_shift_range=0.05,
                height_shift_range=0.05)
    
    image_generator.fit(train_images, augment=True)
    
    randidx = np.random.randint(60000, size=40000)
    x_augmented = train_images[randidx].copy()
    y_augmented = train_labels[randidx].copy()
    x_augmented = image_generator.flow(x_augmented, np.zeros(40000), batch_size=40000, shuffle=False).next()[0]
    train_images = np.concatenate((train_images, x_augmented))
    train_labels = np.concatenate((train_labels, y_augmented))
    return [train_images, train_labels]
# =============================================================================
[train_images, train_labels] = elastic_augmentor(train_images, train_labels)

# =============================================================================
#save
#with open("train.pkl", "wb") as f:
#        pkl.dump([train_images, train_labels], f)
# =============================================================================
#with open("train.pkl", "rb") as f:
#    train_images, train_labels = pkl.load(f)


network = Sequential()
network.add(Conv2D(32, kernel_size=3, activation='relu', input_shape=(28,28,1)))
network.add(Conv2D(64, kernel_size=3, activation='relu'))
network.add(Conv2D(128, kernel_size=3, activation='relu'))
network.add(MaxPooling2D(pool_size=(2,2)))
network.add(Conv2D(128, kernel_size=3, activation='relu'))
network.add(MaxPooling2D(pool_size=(2,2)))
network.add(Dropout(rate=0.2))
network.add(Flatten())
network.add(Dense(128, activation='relu'))
network.add(Dropout(rate=0.5))
network.add(Dense(num_classes, activation='softmax'))

network.compile(optimizer='adamax', loss='categorical_crossentropy', metrics=['accuracy'])

start_time = time.time()
history = network.fit(train_images, train_labels, epochs=epochs, batch_size=batch_size, verbose=1, validation_data=(test_images, test_labels))
elapsed_time = time.time() - start_time

plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.title('Model loss')
plt.ylabel('Loss')
plt.xlabel('Epoch')
plt.legend(['Train', 'Test'], loc='upper left')
plt.show() 

score = network.evaluate(test_images, test_labels, verbose=0)

print('test_acc:', score[1])
print('Test fails', "%0.2f" %(100 - score[1]*100))
print('Time: ', "%0.2f" %elapsed_time)