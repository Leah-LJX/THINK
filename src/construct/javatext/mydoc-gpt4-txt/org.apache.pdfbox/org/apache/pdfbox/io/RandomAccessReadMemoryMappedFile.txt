[API Name] org.apache.pdfbox.io.RandomAccessReadMemoryMappedFile
[Type] Class
[Info]
All Implemented Interfaces: Closeable, AutoCloseable, RandomAccessRead
public class RandomAccessReadMemoryMappedFile extends Object implements RandomAccessRead An implementation of the RandomAccess interface backed by a memory mapped file channel. The whole file is mapped to  memory and the max size is limited to Integer.MAX_VALUE.
[Constructor Summary]
RandomAccessReadMemoryMappedFile(File file)	Create a random access memory mapped file instance for the given file.
RandomAccessReadMemoryMappedFile(Path path)	Create a random access memory mapped file instance using the given path.
RandomAccessReadMemoryMappedFile(String filename)	Create a random access memory mapped file instance for the file with the given name.
[Method Summary]
void close()	
RandomAccessReadView createView(long startPosition, long streamLength)	Creates a random access read view starting at the given position with the given length.
long getPosition()	Returns offset of next byte to be returned by a read method.
boolean isClosed()	Returns true if this source has been closed.
boolean isEOF()	A simple test to see if we are at the end of the data.
long length()	The total number of bytes that are available.
int read()	Read a single byte of data.
int read(byte[] b, int offset, int length)	Read a buffer of data.
void seek(long position)	Seek to a position in the data.
[Methods inherited from class java.lang.Object]
clone, equals, finalize, getClass, hashCode, notify, notifyAll, toString, wait, wait, wait
[Methods inherited from interface org.apache.pdfbox.io.RandomAccessRead]
available, peek, read, rewind, skip