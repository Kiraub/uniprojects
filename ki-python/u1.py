#!/usr/bin/python2

"""
    Tasks for exercise 1 -- introduction to python
"""

# (1)

def isPrime(n):
    x = 2
    while x < n:
        if n % x == 0:
            return False
        x += 1
    return True

# (2)

primList = filter(isPrime, [x for x in range(1000) ])

print(primList[17])

# (3)

import datetime

class Person(object):
    def __init__(self,name,surname,birthday,address):
        self.name = name
        self.surname = surname
        self.birthday = datetime.date(birthday.year,birthday.month,birthday.day)
        self.address = address
    def age(self):
        t = datetime.date.today()
        y = t.year
        m = t.month
        d = t.day
        if m < self.birthday.month and d < self.birthday.day:
            y -= 1
        print('Age is ' + str(y - self.birthday.year) + ' years')
    def fullname(self):
        return self.name + ' ' + self.surname

otto = Person('otto','normal',datetime.date(1997,8,23),'stadt land fluss')
otto.age()

# (4)

class Course(object):
    def __init__(self):
        self.participants = {}
    def getParticipants(self):
        for p in self.participants:
            print('ID: ' + str(p) + '\nName: ' + self.participants[p].fullname())
    def addParticipant(self,id,person):
        self.participants[id] = person

ki = Course();
ki.addParticipant(0,otto)
ki.getParticipants()