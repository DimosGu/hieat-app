#!/bin/sh

curl -v -XPOST -H 'content-type: application/json' \
  -d '{"account":"13883712048","password":"jingyang","code":"234123"}' \
  http://localhost:9000/inapi/user/signUp
echo ''
