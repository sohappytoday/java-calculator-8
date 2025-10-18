package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 여러_커스텀_연산자_호출() {
        assertSimpleTest(() -> {
            run("//;\\n1;2a:3,4//a\\n");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 커스텀_연산자_어디서든_호출() {
        assertSimpleTest(() -> {
            run("1;2//;\\n:3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 커스텀_연산자_여러문자_허용() {
        assertSimpleTest(() -> {
            run("//ab\\n1ab2ab3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_연산자_null_또는_숫자_불가능() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//\\n1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//1\\n1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 커스텀_연산자에_슬래시_허용_바_엔_불가() {
        assertSimpleTest(() -> {
            run("////\\n1//2//3//4");
            assertThat(output()).contains("결과 : 10");
        });
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//\\n\\n1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 커스텀_연산자에_기본연산자_포함_가능() {
        assertSimpleTest(() -> {
            run("//:,\\n1:,2:,3,4:5");
            assertThat(output()).contains("결과 : 15");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
