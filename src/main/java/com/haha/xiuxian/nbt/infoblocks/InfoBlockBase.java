package com.haha.xiuxian.nbt.infoblocks;

/**
 * <p color="red">
 * {@code InfoBlockBase} 接口的一个实现示例：
 * </p>
 * <pre>
 * {@code
 * public class InfoBlock? implements InfoBlockBase&lt;?&gt; {
 *     private ? value;
 *
 *     public InfoBlockInt(? value) {
 *         this.value = value;
 *     }
 *
 *     @Override
 *     public ? getValue() {
 *         return value;
 *     }
 *
 *     @Override
 *     public void setValue(? value) {
 *         this.value = value;
 *     }
 * }
 * }
 * </pre>
 *
 * <p>
 * 此类是一种通用结构，用于存储和检索任意类型的值。
 * </p>
 *
 * @param <T> 泛型类型，表示存储的值的类型
 * @author <a href="https://github.com/HaHaoooo">haha</a>
 * @since 1.0
 * @version 1.2
 * @date 2024年9月8日
 */
public interface InfoBlockBase<T> {

    T getValue();

    void setValue(T value);
}