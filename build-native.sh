#!/bin/bash

set -e

echo "🚀 Building Quarkus Native binary inside Docker (cache .m2)..."

docker run --rm -it \
  -v ~/.m2/repository:/root/.m2/repository:rw \
  -v $PWD:/workspace:rw \
  -w /workspace \
  vegardit/graalvm-maven:latest-java21 \
  mvn clean install -Pnative \
  -Dquarkus.profile=native

echo "✅ Native binary build completed."

echo "🚀 Building Docker Image..."

docker build -t codesmell-api:latest .

echo "✅ Docker image build completed."