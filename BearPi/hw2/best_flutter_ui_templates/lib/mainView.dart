import 'dart:convert';
import 'dart:io';

import 'package:best_flutter_ui_templates/app_theme.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class HomeView extends StatefulWidget {
  @override
  _HomeView createState() => _HomeView();
}

class _HomeView extends State<HomeView> {
  var _switchFan = false;
  var _switchLight = false;
  var _smartFan = false;
  var _smartLight = false;

  switchLightFunc() async {
    var tmp = "";
    if (_switchLight) {
      tmp = "ON";
    } else {
      tmp = "OFF";
    }
    var user = {'value': tmp};
    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    print(userbody);
    String msg = "";

    var url = "http://192.168.1.109:8080/switchLight";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    var map = json.decode(decode.convert(response.bodyBytes));
    print(map);
  }

  switchFanFunc() async {
    var tmp = "";
    if (_switchFan) {
      tmp = "ON";
    } else {
      tmp = "OFF";
    }
    var user = {'value': tmp};
    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    print(userbody);
    String msg = "";

    var url = "http://192.168.1.109:8080/switchFan";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    var map = json.decode(decode.convert(response.bodyBytes));
    print(map);
  }

  smartLightFunc() async {
    var tmp = "";
    if (_smartLight) {
      tmp = "ON";
    } else {
      tmp = "OFF";
    }
    var user = {'value': tmp};
    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    print(userbody);
    String msg = "";

    var url = "http://192.168.1.109:8080/smartLight";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    var map = json.decode(decode.convert(response.bodyBytes));
    print(map);
  }

  smartFanFunc() async {
    var tmp = "";
    if (_smartFan) {
      tmp = "ON";
    } else {
      tmp = "OFF";
    }
    var user = {'value': tmp};
    print(user);
    JsonCodec jc = new JsonCodec();
    Object userbody = jc.encode(user);
    print(userbody);
    String msg = "";

    var url = "http://192.168.1.109:8080/smartFan";
    var httpClient = new HttpClient();
    var response = await http.post(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: userbody);
    Utf8Decoder decode = new Utf8Decoder();
    print(response);
    var map = json.decode(decode.convert(response.bodyBytes));
    print(map);
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color: AppTheme.nearlyWhite,
      child: SafeArea(
        top: false,
        child: Scaffold(
          backgroundColor: AppTheme.nearlyWhite,
          body: Column(
            children: <Widget>[
              Center(
                  child: CupertinoNavigationBar(
                middle: Text('Welcome to Tyco'),
              )),
              // Container(
              //   child: Align(
              //     child: DataTable(
              //       columns: [
              //         DataColumn(label: Text('')),
              //         DataColumn(label: Text('')),
              //       ],
              //       rows: [
              //         DataRow(cells: [
              //           DataCell(Text('环境温度')),
              //           DataCell(Text('18℃')),
              //         ]),
              //         DataRow(cells: [
              //           DataCell(Text('环境湿度')),
              //           DataCell(Text('18RH')),
              //         ]),
              //         DataRow(cells: [
              //           DataCell(Text('环境光强')),
              //           DataCell(Text('18cd')),
              //         ]),
              //       ],
              //     ),
              //   ),
              // ),
              SwitchListTile(
                title: Text('开启灯光'),
                value: _switchLight,
                onChanged: (value) {
                  if (!_smartLight) {
                    setState(() {
                      _switchLight = value;
                      switchLightFunc();
                    });
                  }
                },
              ),
              SwitchListTile(
                title: Text('开启风扇'),
                value: _switchFan,
                onChanged: (value) {
                  if (!_smartFan) {
                    setState(() {
                      _switchFan = value;
                      switchFanFunc();
                    });
                  }
                },
              ),
              SwitchListTile(
                title: Text('开启灯光智能调节模式'),
                value: _smartLight,
                onChanged: (value) {
                  if (value) {
                    _switchLight = false;
                  }
                  setState(() {
                    _smartLight = value;
                  });
                  smartLightFunc();
                },
              ),
              SwitchListTile(
                title: Text('开启风扇智能调节模式'),
                value: _smartFan,
                onChanged: (value) {
                  if (value) {
                    _switchFan = false;
                  }
                  setState(() {
                    _smartFan = value;
                  });
                  smartFanFunc();
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
