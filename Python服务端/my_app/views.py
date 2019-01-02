from .models import User, Record
from django.http import JsonResponse
from django.shortcuts import HttpResponse, render, redirect
from django.contrib.auth import authenticate
import datetime
# Create your views here.


def query_phone(phone_number, password):
    if User.objects.get(phone_number=phone_number) is None:
        return False
    elif User.objects.get(phone_number=phone_number).password != password:
        return False
    else:
        return True


def register(request):
    if request.method == 'POST':
        data = request.POST
       #  print(data['password'])
        temp = []
        if (data['phone_number'] is not None) and (data['password'] is not None):
            if len(User.objects.filter(phone_number=data['phone_number'])) == 0:
                User.objects.create(phone_number=data['phone_number'], password=data['password'])
                return get_json([], 200, 'Register Successfully!')
            else:
                return get_json(temp, 404, 'Register Failed!')
        else:
            return get_json(temp, 404, 'Register Failed!')
    else:
        return JsonResponse({'code': 404, 'message': 'test OK'})


def login(request):
    response = dict()
    if request.method == 'POST':
        data = request.POST
        if query_phone(data['phone_number'], data['password']):
            request.session['phone_number'] = data['phone_number']
            print(request.session.get('phone_number'))
            return get_json([], 200, 'Login Successfully!')
        else:
            return get_json([{'phone_number': data['phone_number']}], 404, 'Login Failed!')
    else:
        return JsonResponse({'message': 'test OK'})


def logout(request):
    temp = []
    if request.method == 'GET':
        print(request.session.get('phone_number'))
        if request.session.get('phone_number', None) is not None:
            del request.session['phone_number']
            return get_json(temp, 200, 'Logout Successfully!')
        else:
            return get_json(temp, 200, 'Logout Successfully!')
            # return get_json(temp, 404, 'Logout Failed!')
    else:
        return JsonResponse({'message': 'test OK'})


def upload(request):
    temp = []
    if request.method == 'POST':
        data = request.POST
        print(data['money'])
        Record.objects.create(phone_number=data['phone_number'], record_date=data['record_date'], flag=data['flag'], money=data['money'], category=data['category'])
        return get_json(temp, 200, 'upload successfully!')
    else:
        return get_json(temp, 404, 'upload failed!')


def download(request):
    if request.method == 'GET':
        temp_records = Record.objects.filter(phone_number=request.GET.get('phone_number'))
        print(request.GET.get('phone_number'))
        response = []
        for x in temp_records:
            temp = dict()
            temp['record_date'] = x.record_date
            temp['flag'] = x.flag
            temp['money'] = x.money
            temp['category'] = x.category
            response.append(temp)
        return get_json(response, 200, 'DownLoad Successfully!')
    else:
        return get_json([], 404, 'DownLoad Failed!')


def get_json(data, code, message):
    repos = {'data': data, 'code': code, 'message': message}
    return JsonResponse(repos, safe=False)





