#!/bin/bash

echo 'Criando o volume...'
kubectl apply -f volume-pv.yaml
sleep 5
kubectl apply -f volume-pvc.yaml
sleep 5

echo 'Criando secrets e configMap...'
kubectl apply -f secrets.yaml
sleep 7
kubectl apply -f configmap.yaml
sleep 7

echo 'Subindo o banco de dados...'
kubectl apply -f db-deployment.yaml
sleep 15

echo 'Subindo a aplicação...'
kubectl apply -f api-deployment.yaml
