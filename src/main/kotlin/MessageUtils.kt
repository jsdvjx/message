import ke.bb.protobuf.message.MessageModel.*
import java.util.*

fun <T : com.google.protobuf.Message> Message.response(payload: T): Message {
    return Message.newBuilder().let {
        it.type = type
        it.payload = com.google.protobuf.Any.pack(payload)
        it.uuid = uuid
        it.messageType = Message.MessageType.Response
        it.build()
    }
}

fun <T : com.google.protobuf.Message> Message.body(): T {
    val clazz = Class.forName(payload.typeUrl.split("/").first()) as Class<T>
    return payload.unpack(clazz)
}

class MessageUtils {
    companion object {
        fun <T : com.google.protobuf.Message> requestMessage(
            type: Message.Type,
            payload: T
        ): Message {
            return Message.newBuilder().apply {
                this.type = type
                uuid = UUID.randomUUID().toString()
                messageType = Message.MessageType.Request
                this.payload = com.google.protobuf.Any.pack(payload, payload::class.java.name)
            }.build()
        }
    }
}