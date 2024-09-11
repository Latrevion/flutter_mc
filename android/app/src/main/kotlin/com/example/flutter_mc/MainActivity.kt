package com.example.flutter_mc

import android.os.Handler
import android.os.Looper
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.StringCodec

class MainActivity : FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)


        //first,create a channel,the name is "messageChannel",the type is String,it same as Flutter
        val channel = BasicMessageChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "messageChannel",
            StringCodec.INSTANCE
        )


        //second,set a message handler callback ,and return 'ACK from Android'
        channel.setMessageHandler { message, reply ->
            Log.d("flutter", "Android receive message $message")
            reply.reply("ACK from Android")


            Handler(Looper.getMainLooper()).postDelayed({
                //third,send a message to dart
                channel.send("Hello from Android")
            }, 1000)

        }


    }
}
