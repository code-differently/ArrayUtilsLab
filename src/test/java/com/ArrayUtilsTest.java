package com;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class ArrayUtilsTest {

    int[] empty;
    int[] original;
    int[] toBeUpdated;



    @Before
    public void setup(){

        empty = new int[0];

        original = setOriginalValues();
        toBeUpdated = setOriginalValues();

    }

    private int[] setOriginalValues(){
        int[] array = {4, 5, 1, 9, 3, 6, 7, 8, 2, 4};
        return array;
    }

    @Test
    public void testIsEmptyWithEmptyArray() {
        Assert.assertTrue(ArrayUtils.isEmpty(empty));
    }

    @Test
    public void testIsEmptyWithFullArray() {
        Assert.assertFalse(ArrayUtils.isEmpty(original));
    }

    @Test
    public void testAppend() {
        int newValue = 0;
        int[] updated = ArrayUtils.append(original, newValue);
        Assert.assertEquals(original.length + 1, updated.length);
        for(int i = 0; i < original.length; i++){
            Assert.assertEquals(original[i], updated[i]);
        }
        Assert.assertEquals(newValue, updated[10]);
    }

    @Test
    public void testConcat() {
        int[] updated = ArrayUtils.concat(original, original);
        Assert.assertEquals(2* original.length, updated.length);
        for(int i = 0; i < original.length; i++){
            Assert.assertEquals(original[i], updated[i]);
            Assert.assertEquals(original[i], updated[i+original.length]);
        }
    }

    @Test
    public void testInsertWithValidIndex() {

        int index = 3;
        int value = 10;

        int[] updated = ArrayUtils.insert(original, value, index);

        Assert.assertEquals(original.length + 1, updated.length);

        for(int i = 0; i < index; i++){
            Assert.assertEquals(original[i], updated[i]);
        }

        Assert.assertEquals(value, updated[index]);

        for(int i = index; i < original.length; i++){
            Assert.assertEquals(original[i], updated[i+1]);
        }

    }

    @Test
    public void testInsertWithInvalidIndex() {
        int[] updated = ArrayUtils.insert(original, 3, -1);
        Assert.assertArrayEquals(original, updated);
    }

    @Test
    public void testTestClone() {
        int[] clone = ArrayUtils.clone(original);
        Assert.assertEquals(original.length, clone.length);
        for(int i = 0; i < original.length; i++){
            Assert.assertEquals(original[i], clone[i]);
        }
    }

    @Test
    public void testSubArray() {
        int indexOne = 5;
        int indexTwo = 9;
        int[] result = ArrayUtils.subArray(original, indexOne, indexTwo);

        Assert.assertEquals(result.length, indexTwo-indexOne);
        for(int i = indexOne; i < indexTwo; i++){
            Assert.assertEquals(original[i], result[i-indexOne]);
        }
    }

    @Test
    public void testEqualsOne() {
        Assert.assertTrue(ArrayUtils.equals(original, toBeUpdated));
    }

    @Test
    public void testEqualsTwo() {
        toBeUpdated[6] = 11;
        Assert.assertFalse(ArrayUtils.equals(original, toBeUpdated));
    }

    @Test
    public void testFill() {
        int value = 6;
        ArrayUtils.fill(toBeUpdated, value);
        for(int i : toBeUpdated){
            Assert.assertEquals(value, i);
        }
    }

    @Test
    public void testTestToStringOne() {
        int[] array = {3,7,1};
        Assert.assertEquals("{3,7,1}", ArrayUtils.toString(array));
    }

    @Test
    public void testTestToStringTwo() {
        int[] array = {};
        Assert.assertEquals("{}", ArrayUtils.toString(array));
    }

    @Test
    public void testContainsExistingValue() {
        Assert.assertTrue(ArrayUtils.contains(original, 4));
    }

    @Test
    public void testContainsMissingValue() {
        Assert.assertFalse(ArrayUtils.contains(original, 11));
    }

    @Test
    public void testIndexOfExistingValue() {
        Assert.assertEquals(0, ArrayUtils.indexOf(original, 4));
    }

    @Test
    public void testIndexOfMissingValue() {
        Assert.assertEquals(-1, ArrayUtils.indexOf(original, 11));
    }

    @Test
    public void testRemoveExistingValue() {
        int value = 9;
        int[] updated = ArrayUtils.remove(original, value);

        Assert.assertEquals(original.length-1, updated.length);
        for(int i = 0; i < 3; i++){
            Assert.assertEquals(original[i], updated[i]);
        }
        for(int i = 3; i < updated.length; i++){
            Assert.assertEquals(original[i+1], updated[i]);
        }

    }

    @Test
    public void testRemoveMissingValue() {
        int[] updated = ArrayUtils.remove(original, 10);
        Assert.assertArrayEquals(original, updated);
    }

    @Test
    public void testReverse() {
        ArrayUtils.reverse(toBeUpdated);
        Assert.assertNotEquals(original[1], toBeUpdated[1]);
        ArrayUtils.reverse(toBeUpdated);
        Assert.assertArrayEquals(original, toBeUpdated);
    }

    @Test
    public void testShiftOne() {

        int offset = 3;
        ArrayUtils.shift(toBeUpdated, offset);

        for(int i = 0; i < original.length; i++){
            int newIndex = (i + offset) % original.length;
            Assert.assertEquals(original[i], toBeUpdated[newIndex]);
        }

    }

    @Test
    public void testShiftTwo() {

        int offset = -4;
        ArrayUtils.shift(toBeUpdated, offset);

        offset = original.length + offset;
        for(int i = 0; i < original.length; i++){
            int newIndex = (i + offset) % original.length;
            Assert.assertEquals(original[i], toBeUpdated[newIndex]);
        }

    }

    @Test
    public void testShiftThree() {

        int offset = 0;
        ArrayUtils.shift(toBeUpdated, offset);

        for(int i = 0; i < original.length; i++){
            Assert.assertEquals(original[i], toBeUpdated[i]);
        }

    }

    @Test
    public void testShiftFour() {

        int offset = 20;
        ArrayUtils.shift(toBeUpdated, offset);

        for(int i = 0; i < original.length; i++){
            int newIndex = (i + offset) % original.length;
            Assert.assertEquals(original[i], toBeUpdated[newIndex]);
        }

    }

    @Test
    public void testSwap() {

        int indexOne = 3;
        int indexTwo = 7;

        ArrayUtils.swap(toBeUpdated, indexOne, indexTwo);

        Assert.assertEquals(original[indexOne], toBeUpdated[indexTwo]);
        Assert.assertEquals(original[indexTwo], toBeUpdated[indexOne]);

    }

    @Test
    public void copyTestWithInvalidInput(){
        ArrayUtils.copy(original, empty);
        Assert.assertEquals(0, empty.length);
    }

    @Test
    public void copyTestWithValidInput(){
        int[] updated = new int[original.length];
        ArrayUtils.copy(original, updated);
        for(int i = 0; i < original.length; i++){
            Assert.assertEquals(original[i], updated[i]);
        }
    }
}