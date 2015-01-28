/*
 * FRC 4931 (http://www.evilletech.com)
 *
 * Open source software. Licensed under the FIRST BSD license file in the
 * root directory of this project's Git repository.
 */
package org.frc4931.robot.component;

import edu.wpi.first.wpilibj.SerialPort;
import org.frc4931.utils.Lifecycle;

import java.nio.ByteBuffer;

public class SerialDataStream implements DataStream, Lifecycle {
    private SerialPort serial;

    public SerialDataStream(SerialPort serial) {
        this.serial = serial;
    }

    @Override
    public int available() {
        return serial.getBytesReceived();
    }

    @Override
    public byte[] read(int count) {
        return serial.read(count);
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
    public int write(byte... data) {
        return serial.write(data, data.length);
    }

    @Override
    public void writeByte(byte value) {
        write(value);
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
        serial.flush();
    }

    @Override
    public void startup() {

    }

    @Override
    public void shutdown() {
        serial.free();
    }
}
