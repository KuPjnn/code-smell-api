#!/bin/bash

set -e

echo "🚀 Building Quarkus Native binary inside Docker (cache .m2)..."

docker run --rm -it \
  -v ~/.m2/repository:/root/.m2/repository:rw \
  -v $PWD:/workspace:rw \
  -w /workspace \
  vegardit/graalvm-maven:latest-java21 \
  mvn clean package -Pnative -DskipTests -DskipITs \
  -Dquarkus.native.skip-jar=true \
  -Dquarkus.native.native-image-xmx=4g \
  -Dquarkus.native.additional-build-args=--parallelism=4

echo "✅ Native binary build completed."

echo "🚀 Building Docker Image..."

docker build -t codesmell-api:latest .

echo "✅ Docker image build completed."