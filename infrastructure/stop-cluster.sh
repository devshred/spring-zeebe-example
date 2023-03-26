#!/bin/sh

### delete Kubernetes cluster
#k3d cluster delete camunda-platform
kind delete clusters camunda-platform
