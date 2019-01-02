from django.db import models
import pytz
from django.db import models

from datetime import datetime


class User(models.Model):
    phone_number = models.CharField(max_length=30, unique=True)
    created_at = models.DateTimeField(default=datetime.now().replace(tzinfo=pytz.timezone('UTC')))
    password = models.CharField(max_length=30)


class Record(models.Model):
    phone_number = models.CharField(max_length=30)
    flag = models.IntegerField()
    record_date = models.CharField(max_length=100)
    money = models.DecimalField(max_digits=10, decimal_places=2)
    category = models.CharField(max_length=30)

