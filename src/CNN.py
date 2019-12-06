# -*- coding: utf-8 -*-
"""
Created on Fri Dec  6 18:25:57 2019

@author: Fernando
"""

from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential, load_model
from tensorflow.keras.layers import Dense, Conv2D, Flatten, MaxPooling2D, Dropout
from tensorflow.keras.optimizers import RMSprop, SGD
from tensorflow.keras.utils import to_categorical

import time
import matplotlib.pyplot as plt

batch_size = 64
num_classes = 10
epochs = 10 


(train_images, train_labels), (test_images, test_labels) = mnist.load_data()

train_images = train_images.reshape((60000, 28,28,1))
test_images = test_images.reshape((10000, 28,28,1))                                  

train_labels = to_categorical(train_labels)
test_labels = to_categorical(test_labels)

network = Sequential()
network.add(Conv2D(64, kernel_size=3, activation='relu', input_shape=(28,28,1)))
network.add(MaxPooling2D(pool_size=(2,2)))
network.add(Conv2D(32, kernel_size=3, activation='relu'))
network.add(MaxPooling2D(pool_size=(2,2)))
network.add(Dropout(rate=0.2))
network.add(Flatten())
network.add(Dense(512, activation='relu'))
network.add(Dropout(rate=0.2))
network.add(Dense(128, activation='relu'))
network.add(Dropout(rate=0.2))
network.add(Dense(num_classes, activation='softmax'))

network.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

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