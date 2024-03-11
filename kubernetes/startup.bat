echo 'Criando o volume...'
kubectl apply -f volume-pv.yaml
timeout /t 5
kubectl apply -f volume-pvc.yaml
timeout /t 5

echo 'Criando secrets e configMap...'
kubectl apply -f secrets.yaml
timeout /t 7
kubectl apply -f configmap.yaml
timeout /t 7

echo 'Criando o HPA...'
kubectl apply -f api-hpa.yaml
timeout /t 5

echo 'Subindo o banco de dados...'
kubectl apply -f db-deployment.yaml
timeout /t 15

echo 'Subindo a aplicação...'
kubectl apply -f api-deployment.yaml