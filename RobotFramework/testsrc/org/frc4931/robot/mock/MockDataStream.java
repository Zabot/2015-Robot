/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot.mock;

import java.nio.ByteBuffer;
import java.util.PriorityQueue;
import java.util.Queue;

import org.frc4931.robot.component.DataStream;

/**
 * A mock implementation of {@link org.frc4931.robot.component.DataStream} that does not require hardware.
 */
public class MockDataStream implements DataStream {
    private Queue<Byte> readData;
    private Queue<Byte> writtenData;
    private Queue<Byte> flushedData;

    /**
     * Creates a new data stream.
     */
    public MockDataStream() {
        readData = new PriorityQueue<>();
        writtenData = new PriorityQueue<>();
        flushedData = new PriorityQueue<>();
    }

    /**
     * Puts data in the read buffer (for the class to access using read())
     *
     * @param data The data to add to the buffer.
     */
    public void addReadData(byte... data) {
        for (byte b : data) {
            readData.add(b);
        }
    }

    /**
     * Puts a byte in the read buffer.
     *
     * @param value The byte to add.
     */
    public void addByte(byte value) {
        addReadData(value);
    }

    /**
     * Puts a short in the read buffer.
     *
     * @param value The short to add.
     */
    public void addShort(short value) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.putShort(value);
        buffer.flip();

        addReadData(buffer.array());
    }

    /**
     * Puts an int in the read buffer.
     *
     * @param value The int to add.
     */
    public void addInt(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        buffer.flip();

        addReadData(buffer.array());
    }

    /**
     * Puts a long in the read buffer.
     *
     * @param value The long to add.
     */
    public void addLong(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(value);
        buffer.flip();

        addReadData(buffer.array());
    }

    /**
     * Puts a float in the read buffer.
     *
     * @param value The float to add.
     */
    public void addFloat(float value) {
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        buffer.putFloat(value);
        buffer.flip();

        addReadData(buffer.array());
    }

    /**
     * Puts a double in the read buffer.
     *
     * @param value The double to add.
     */
    public void addDouble(double value) {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(value);
        buffer.flip();

        addReadData(buffer.array());
    }

    /**
     * Gets data flushed by the class.
     * <p>
     * @param count The number of bytes to retrieve.
     *
     * @return The bytes retrieved - may be less than requested number.
     */
    public byte[] getFlushedData(int count) {
        byte[] data = new byte[Math.min(count, flushedData.size())];

        for (int i = 0; i < data.length; i++) {
            data[i] = flushedData.remove();
        }

        return data;
    }

    /**
     * Gets a flushed byte.
     *
     * @return The byte retrieved.
     */
    public byte getByte() {
        byte[] bytes = getFlushedData(Byte.BYTES);

        return ByteBuffer.wrap(bytes).get();
    }

    /**
     * Gets a flushed short
     *
     * @return The short retrieved.
     */
    public short getShort() {
        byte[] bytes = getFlushedData(Short.BYTES);

        return ByteBuffer.wrap(bytes).get();
    }

    /**
     * Gets a flushed integer.
     *
     * @return The integer retrieved.
     */
    public int getInt() {
        byte[] bytes = getFlushedData(Integer.BYTES);

        return ByteBuffer.wrap(bytes).get();
    }

    /**
     * Gets a flushed long.
     *
     * @return The long retrieved.
     */
    public long getLong() {
        byte[] bytes = getFlushedData(Long.BYTES);

        return ByteBuffer.wrap(bytes).get();
    }

    /**
     * Gets a flushed float.
     *
     * @return The float retrieved.
     */
    public float getFloat() {
        byte[] bytes = getFlushedData(Float.BYTES);

        return ByteBuffer.wrap(bytes).get();
    }

    /**
     * Gets a flushed double.
     *
     * @return The double retrieved.
     */
    public double getDouble() {
        byte[] bytes = getFlushedData(Double.BYTES);

        return ByteBuffer.wrap(bytes).get();
    }

    @Override
    public int available() {
        return readData.size();
    }

    @Override
    public byte[] read(int count) {
        byte[] result = new byte[Math.max(count, readData.size())];
        for (int i = 0; i < result.length; i++) {
            result[i] = readData.remove();
        }
        return result;
    }

    @Override
    public byte readByte() {
        byte[] bytes = read(Byte.BYTES);

        return ByteBuffer.wrap(bytes).get();
    }

    @Override
    public short readShort() {
        byte[] bytes = read(Short.BYTES);

        return ByteBuffer.wrap(bytes).getShort();
    }

    @Override
    public int readInt() {
        byte[] bytes = read(Integer.BYTES);

        return ByteBuffer.wrap(bytes).getInt();
    }

    @Override
    public long readLong() {
        byte[] bytes = read(Long.BYTES);

        return ByteBuffer.wrap(bytes).getLong();
    }

    @Override
    public float readFloat() {
        byte[] bytes = read(Float.BYTES);

        return ByteBuffer.wrap(bytes).getFloat();
    }

    @Override
    public double readDouble() {
        byte[] bytes = read(Double.BYTES);

        return ByteBuffer.wrap(bytes).getDouble();
    }

    @Override
    public int write(byte[] data) {
        int writeCount = 0;
        for (byte b : data) {
            if (writtenData.offer(b)) {
                writeCount++;
            }
        }

        return writeCount;
    }

    @Override
    public void writeByte(byte value) {
        write(new byte[]{value});
    }

    @Override
    public void writeShort(short value) {
        ByteBuffer buffer = ByteBuffer.allocate(Short.BYTES);
        buffer.putShort(value);
        buffer.flip();

        write(buffer.array());
    }

    @Override
    public void writeInt(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        buffer.flip();

        write(buffer.array());
    }

    @Override
    public void writeLong(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(value);
        buffer.flip();

        write(buffer.array());
    }

    @Override
    public void writeFloat(float value) {
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES);
        buffer.putFloat(value);
        buffer.flip();

        write(buffer.array());
    }

    @Override
    public void writeDouble(double value) {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES);
        buffer.putDouble(value);
        buffer.flip();

        write(buffer.array());
    }

    @Override
    public void flush() {
        flushedData.addAll(writtenData);
        writtenData.clear();
    }

    @Override
    public void startup() {

    }

    @Override
    public void shutdown() {
        readData.clear();
        writtenData.clear();
        flushedData.clear();
    }
}
