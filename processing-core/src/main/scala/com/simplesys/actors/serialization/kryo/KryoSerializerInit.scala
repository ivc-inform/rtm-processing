package com.simplesys.actors.serialization.kryo

import com.esotericsoftware.kryo.Kryo
import org.joda.time.{DateTime, LocalDateTime, LocalTime}

class KryoSerializerInit {
    def customize(kryo: Kryo): Unit = {
        kryo.register(classOf[DateTime], new JodaDateTimeKryoSerializer())
        kryo
          .register(classOf[LocalDateTime], new JodaLocalDateTimeKryoSerializer())
        kryo.register(classOf[LocalTime], new JodaLocalTimeKryoSerializer())
    }
}
