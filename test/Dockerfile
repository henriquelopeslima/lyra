FROM google/dart

ENV TZ="America/Sao_Paulo"

WORKDIR /app

ADD pubspec.* /app/
RUN pub get
ADD . /app
RUN pub get --offline
