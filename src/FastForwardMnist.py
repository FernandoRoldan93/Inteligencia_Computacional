# -*- coding: utf-8 -*-
"""
Created on Wed Dec  4 01:33:39 2019

@author: Fernando
"""

from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential,load_model
from tensorflow.keras.layers import Dense, Dropout
from tensorflow.keras.optimizers import RMSprop, SGD
from tensorflow.keras.utils import to_categorical

import time
import matplotlib.pyplot as plt

batch_size = 256
num_classes = 10
epochs = 30


(train_images, train_labels), (test_images, test_labels) = mnist.load_data()

train_images = train_images.reshape((60000, 28 * 28))
train_images = train_images.astype('float32') / 255
                                  
test_images = test_images.reshape((10000, 28*28))                                  
test_images = test_images.astype('float32') / 255

train_labels = to_categorical(train_labels)
test_labels = to_categorical(test_labels)

network = Sequential()
network.add(Dense(1024, activation='relu', input_shape=(28*28,)))
network.add(Dropout(rate=0.5))
network.add(Dense(1024, activation='relu'))
network.add(Dense(num_classes, activation='softmax'))

optimizer = SGD(learning_rate=0.1, momentum=0.7, nesterov=False)

network.compile(optimizer=optimizer, loss='categorical_crossentropy', metrics=['accuracy'])

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