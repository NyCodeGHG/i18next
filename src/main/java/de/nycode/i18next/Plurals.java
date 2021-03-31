package de.nycode.i18next;

import java.util.Map;

/**
 * @author stan
 */
public class Plurals {

  /**
   * Used locally to tag Logs
   */
  @SuppressWarnings("unused")
  private static final String TAG = Plurals.class.getSimpleName();

  public Map<String, PluralRule> mHashPlurals;

  /**
   * SingletonHolder is loaded on the first execution of Singleton.getInstance() or the first access
   * to SingletonHolder.INSTANCE, not before.
   */
  private static class SingletonHolder {

    public static final Plurals INSTANCE = new Plurals();
  }

  public static Plurals getInstance() {
    return SingletonHolder.INSTANCE;
  }

  private Plurals() {
    //noinspection RedundantTypeArguments (explicit type arguments speedup compilation and analysis time)
    mHashPlurals = Map.<String, PluralRule>ofEntries(
        Map.entry("ach", buildGreaterThan()),
        Map.entry("af", buildNotEqual(1, 2, 1)),
        Map.entry("ak", buildGreaterThan()),
        Map.entry("am", buildGreaterThan()),
        Map.entry("an", buildNotEqual(1, 2, 1)),
        Map.entry("ar", new PluralRule(0, 1, 2, 3, 11, 100) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n == 0 ? 0
                : n == 1 ? 1
                    : n == 2 ? 2 : n % 100 >= 3 && n % 100 <= 10 ? 3 : n % 100 >= 11 ? 4 : 5);
          }
        }),
        Map.entry("arn", buildGreaterThan()),
        Map.entry("ast", buildNotEqual(1, 2, 1)),
        Map.entry("ay", build0()),
        Map.entry("az", buildNotEqual(1, 2, 1)),
        Map.entry("be", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 == 1 && n % 100 != 11 ? 0
                : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("bg", buildNotEqual(1, 2, 1)),
        Map.entry("bn", buildNotEqual(1, 2, 1)),
        Map.entry("bo", build0()),
        Map.entry("br", buildGreaterThan()),
        Map.entry("bs", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 == 1 && n % 100 != 11 ? 0
                : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("ca", buildNotEqual(1, 2, 1)),
        Map.entry("cgg", build0()),
        Map.entry("cs", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt((n == 1) ? 0 : (n >= 2 && n <= 4) ? 1 : 2);
          }
        }),
        Map.entry("csb", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt(
                n == 1 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("cy", new PluralRule(1, 2, 3, 8) {
          @Override
          protected int getPlurals(int n) {
            return getInt((n == 1) ? 0 : (n == 2) ? 1 : (n != 8 && n != 11) ? 2 : 3);
          }
        }),
        Map.entry("da", buildNotEqual(1, 2, 1)),
        Map.entry("de", buildNotEqual(1, 2, 1)),
        Map.entry("dz", build0()),
        Map.entry("el", buildNotEqual(1, 2, 1)),
        Map.entry("en", buildNotEqual(1, 2, 1)),
        Map.entry("eo", buildNotEqual(1, 2, 1)),
        Map.entry("es", buildNotEqual(1, 2, 1)),
        Map.entry("es_ar", buildNotEqual(1, 2, 1)),
        Map.entry("et", buildNotEqual(1, 2, 1)),
        Map.entry("eu", buildNotEqual(1, 2, 1)),
        Map.entry("fa", build0()),
        Map.entry("fi", buildNotEqual(1, 2, 1)),
        Map.entry("fil", buildGreaterThan()),
        Map.entry("fo", buildNotEqual(1, 2, 1)),
        Map.entry("fr", buildGreaterThan()),
        Map.entry("fur", buildNotEqual(1, 2, 1)),
        Map.entry("fy", buildNotEqual(1, 2, 1)),
        Map.entry("ga", new PluralRule(1, 2, 3, 7, 11) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n == 1 ? 0 : n == 2 ? 1 : n < 7 ? 2 : n < 11 ? 3 : 4);
          }
        }),
        Map.entry("gd", new PluralRule(1, 2, 3, 20) {
          @Override
          protected int getPlurals(int n) {
            return getInt(
                (n == 1 || n == 11) ? 0 : (n == 2 || n == 12) ? 1 : (n > 2 && n < 20) ? 2 : 3);
          }
        }),
        Map.entry("gl", buildNotEqual(1, 2, 1)),
        Map.entry("gu", buildNotEqual(1, 2, 1)),
        Map.entry("gun", buildGreaterThan()),
        Map.entry("ha", buildNotEqual(1, 2, 1)),
        Map.entry("he", buildNotEqual(1, 2, 1)),
        Map.entry("hi", buildNotEqual(1, 2, 1)),
        Map.entry("hr", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 == 1 && n % 100 != 11 ? 0
                : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("hu", buildNotEqual(1, 2, 1)),
        Map.entry("hy", buildNotEqual(1, 2, 1)),
        Map.entry("ia", buildNotEqual(1, 2, 1)),
        Map.entry("id", build0()),
        Map.entry("is", new PluralRule(1, 2) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 != 1 || n % 100 == 11);
          }
        }),
        Map.entry("it", buildNotEqual(1, 2, 1)),
        Map.entry("ja", build0()),
        Map.entry("jbo", build0()),
        Map.entry("jv", buildNotEqual(0, 1, 0)),
        Map.entry("ka", build0()),
        Map.entry("kk", build0()),
        Map.entry("km", build0()),
        Map.entry("kn", buildNotEqual(1, 2, 1)),
        Map.entry("ko", build0()),
        Map.entry("ku", buildNotEqual(1, 2, 1)),
        Map.entry("kw", new PluralRule(1, 2, 3, 4) {
          @Override
          protected int getPlurals(int n) {
            return getInt((n == 1) ? 0 : (n == 2) ? 1 : (n == 3) ? 2 : 3);
          }
        }),
        Map.entry("ky", build0()),
        Map.entry("lb", buildNotEqual(1, 2, 1)),
        Map.entry("ln", buildGreaterThan()),
        Map.entry("lo", build0()),
        Map.entry("lt", new PluralRule(1, 2, 10) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 == 1 && n % 100 != 11 ? 0
                : n % 10 >= 2 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("lv", new PluralRule(0, 1, 2) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n != 0 ? 1 : 2);
          }
        }),
        Map.entry("mai", buildNotEqual(1, 2, 1)),
        Map.entry("mfe", buildGreaterThan()),
        Map.entry("mg", buildGreaterThan()),
        Map.entry("mi", buildGreaterThan()),
        Map.entry("mk", new PluralRule(1, 2) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n == 1 || n % 10 == 1 ? 0 : 1);
          }
        }),
        Map.entry("ml", buildNotEqual(1, 2, 1)),
        Map.entry("mn", buildNotEqual(1, 2, 1)),
        Map.entry("mnk", new PluralRule(0, 1, 2) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n == 0 ? 0 : n == 1 ? 1 : 2);
          }
        }),
        Map.entry("mr", buildNotEqual(1, 2, 1)),
        Map.entry("ms", build0()),
        Map.entry("mt", new PluralRule(1, 2, 11, 20) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n == 1 ? 0
                : n == 0 || (n % 100 > 1 && n % 100 < 11) ? 1
                    : (n % 100 > 10 && n % 100 < 20) ? 2 : 3);
          }
        }),
        Map.entry("nah", buildNotEqual(1, 2, 1)),
        Map.entry("nap", buildNotEqual(1, 2, 1)),
        Map.entry("nb", buildNotEqual(1, 2, 1)),
        Map.entry("ne", buildNotEqual(1, 2, 1)),
        Map.entry("nl", buildNotEqual(1, 2, 1)),
        Map.entry("nn", buildNotEqual(1, 2, 1)),
        Map.entry("no", buildNotEqual(1, 2, 1)),
        Map.entry("nso", buildNotEqual(1, 2, 1)),
        Map.entry("oc", buildGreaterThan()),
        Map.entry("or", buildNotEqual(2, 1, 1)),
        Map.entry("pa", buildNotEqual(1, 2, 1)),
        Map.entry("pap", buildNotEqual(1, 2, 1)),
        Map.entry("pl", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt(
                n == 1 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("pms", buildNotEqual(1, 2, 1)),
        Map.entry("ps", buildNotEqual(1, 2, 1)),
        Map.entry("pt", buildNotEqual(1, 2, 1)),
        Map.entry("pt_br", buildNotEqual(1, 2, 1)),
        Map.entry("rm", buildNotEqual(1, 2, 1)),
        Map.entry("ro", new PluralRule(1, 2, 20) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n == 1 ? 0 : (n == 0 || (n % 100 > 0 && n % 100 < 20)) ? 1 : 2);
          }
        }),
        Map.entry("ru", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 == 1 && n % 100 != 11 ? 0
                : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("sah", build0()),
        Map.entry("sco", buildNotEqual(1, 2, 1)),
        Map.entry("se", buildNotEqual(1, 2, 1)),
        Map.entry("si", buildNotEqual(1, 2, 1)),
        Map.entry("sk", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt((n == 1) ? 0 : (n >= 2 && n <= 4) ? 1 : 2);
          }
        }),
        Map.entry("sl", new PluralRule(5, 1, 2, 3) {
          @Override
          protected int getPlurals(int n) {
            return getInt(
                n % 100 == 1 ? 1 : n % 100 == 2 ? 2 : n % 100 == 3 || n % 100 == 4 ? 3 : 0);
          }
        }),
        Map.entry("so", buildNotEqual(1, 2, 1)),
        Map.entry("son", buildNotEqual(1, 2, 1)),
        Map.entry("sq", buildNotEqual(1, 2, 1)),
        Map.entry("sr", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 == 1 && n % 100 != 11 ? 0
                : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("su", build0()),
        Map.entry("sv", buildNotEqual(1, 2, 1)),
        Map.entry("sw", buildNotEqual(1, 2, 1)),
        Map.entry("ta", buildNotEqual(1, 2, 1)),
        Map.entry("te", buildNotEqual(1, 2, 1)),
        Map.entry("tg", buildGreaterThan()),
        Map.entry("th", build0()),
        Map.entry("ti", buildGreaterThan()),
        Map.entry("tk", buildNotEqual(1, 2, 1)),
        Map.entry("tr", buildGreaterThan()),
        Map.entry("tt", build0()),
        Map.entry("ug", build0()),
        Map.entry("uk", new PluralRule(1, 2, 5) {
          @Override
          protected int getPlurals(int n) {
            return getInt(n % 10 == 1 && n % 100 != 11 ? 0
                : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
          }
        }),
        Map.entry("ur", buildNotEqual(1, 2, 1)),
        Map.entry("uz", buildGreaterThan()),
        Map.entry("vi", build0()),
        Map.entry("wa", buildGreaterThan()),
        Map.entry("wo", build0()),
        Map.entry("yo", buildNotEqual(1, 2, 1)),
        Map.entry("zh", build0())
    );
  }

  private PluralRule build0() {
    return new PluralRule(1) {
      @Override
      protected int getPlurals(int n) {
        return 0;
      }
    };
  }

  private PluralRule buildGreaterThan() {
    return new PluralRule(1, 2) {
      @Override
      protected int getPlurals(int n) {
        return getInt(n > 1);
      }
    };
  }

  private PluralRule buildNotEqual(int i, int i2, int i3) {
    return new PluralRule(i, i2) {
      @Override
      protected int getPlurals(int n) {
        return getInt(n != i3);
      }
    };
  }

  private int getInt(boolean value) {
    return value ? 1 : 0;
  }

  private int getInt(int value) {
    return value;
  }

  public int get(String lng, int count) {
    String[] parts = lng.split("_");
    PluralRule rule = mHashPlurals.get(parts[0]);
    if (rule != null) {
      int var = rule.getPlurals(count);
      int number = rule.mNumbers[var];
      if (rule.mNumbers.length == 2 && rule.mNumbers[0] == 1) {
        if (number == 2) {
          number = -1; // regular plural
        }
      }
      return number;
    } else {
      return count == 1 ? 1 : -1;
    }
  }

  private static abstract class PluralRule {

    private final int[] mNumbers;

    public PluralRule(int... numbers) {
      mNumbers = numbers;
    }

    protected abstract int getPlurals(int n);
  }
}
