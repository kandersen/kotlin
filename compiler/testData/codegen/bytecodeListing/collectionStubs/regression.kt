// FULL_JDK
import java.util.concurrent.*

class RecordingEvents : Iterable<String> {
    private val received = java.util.concurrent.ConcurrentSkipListSet<String>()
    override fun iterator() = received.iterator()
}