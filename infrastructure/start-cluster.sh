#!/bin/sh

### load helm charts provided by Camunda and NGINX
helm repo add camunda https://helm.camunda.io
helm repo update

### create Kubernetes cluster
kind create cluster --name camunda-platform --config kind-cluster.yaml
# add ingress controller
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

### install Camunda Platform
# using minimal setup as recommended by Camunda
helm install \
  --set operate.ingress.enabled=true \
  --set operate.ingress.host=operate.local \
  --set operate.image.tag=8.2.0-alpha5 \
  --set tasklist.ingress.enabled=true \
  --set tasklist.ingress.host=tasklist.local \
  -f https://raw.githubusercontent.com/camunda/camunda-platform-helm/main/kind/camunda-platform-core-kind-values.yaml \
  camunda \
  camunda/camunda-platform

kubectl port-forward svc/camunda-zeebe-gateway 26500:26500
