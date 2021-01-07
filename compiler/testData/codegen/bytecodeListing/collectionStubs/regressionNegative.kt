class RecordingEvents : Iterable<String> {
    private val received = Array<String>(0) { "" }
    override fun iterator() = received.iterator()
}