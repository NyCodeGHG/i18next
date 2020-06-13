package com.i18next.java;

import java.util.HashMap;
import java.util.Map;

/**
 * @author stan
 */
public interface Operation {

  interface PostOperation extends Operation {

    String postProcess(String source);
  }

  interface PreOperation extends Operation {

    String preProcess(String key);

    String preProcessAfterNoValueFound(String key);
  }

  class MultiPostProcessing implements PostOperation, PreOperation {

    private final Operation[] mOperations;

    public MultiPostProcessing(Operation... operations) {
      mOperations = operations;
    }

    @Override
    public String postProcess(String source) {
      if (mOperations != null) {
        for (Operation operation : mOperations) {
          if (operation instanceof PostOperation) {
            source = ((PostOperation) operation).postProcess(source);
          }
        }
      }
      return source;
    }

    @Override
    public String preProcess(String key) {
      if (mOperations != null) {
        for (Operation operation : mOperations) {
          if (operation instanceof PreOperation) {
            key = ((PreOperation) operation).preProcess(key);
          }
        }
      }
      return key;
    }

    @Override
    public String preProcessAfterNoValueFound(String key) {
      if (mOperations != null) {
        for (Operation operation : mOperations) {
          if (operation instanceof PreOperation) {
            String keyTemp = ((PreOperation) operation).preProcessAfterNoValueFound(key);
            if (keyTemp != null && !keyTemp.equals(key)) {
              return keyTemp;
            }
          }
        }
      }
      return null;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof MultiPostProcessing) {
        Operation[] otherOperations = ((MultiPostProcessing) o).mOperations;
        int nbElement = (mOperations == null) ? 0 : mOperations.length;
        int nbElementOther = (otherOperations == null) ? 0 : otherOperations.length;
        if (nbElement == nbElementOther) {
          if (nbElement != 0) {
            for (int i = 0; i < nbElement; i++) {
              Operation operation1 = mOperations[i];
              Operation operation2 = otherOperations[i];
              if ((operation1 != null && !operation1.equals(operation2)) || (operation1 == null
                  && operation2 == null)) {
                return false;
              }
            }
          }
          return true;
        }
      }
      return false;
    }
  }

  class Plural implements PreOperation, PostOperation {

    private final int mCount;
    private final Interpolation mInterpolation;

    public Plural(float count) {
      this("count", count);
    }

    public Plural(int count) {
      this("count", count);
    }

    public Plural(String key, float count) {
      mInterpolation = new Interpolation(key, Float.toString(count));
      mCount = Math.round(count);
    }

    public Plural(String key, int count) {
      mInterpolation = new Interpolation(key, Integer.toString(count));
      mCount = count;
    }

    @Override
    public String preProcess(String key) {
      if (key != null) {
        int pluralExtension = Plurals.getInstance()
            .get(I18Next.getInstance().getOptions().getLanguage(), mCount);

        if (pluralExtension != 1) {
          // plural
          key = key + I18Next.getInstance().getOptions().getPluralSuffix();
          if (pluralExtension >= 0) {
            // specific plural
            key = key + "_" + pluralExtension;
          }
        }
      }
      return key;
    }

    @Override
    public String preProcessAfterNoValueFound(String key) {
      int index = key.lastIndexOf(I18Next.getInstance().getOptions().getPluralSuffix());
      if (index > 0) {
        return key.substring(0, index);
      } else {
        return null;
      }
    }

    @Override
    public String postProcess(String source) {
      return mInterpolation.postProcess(source);
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Plural) {
        Plural oPlural = (Plural) o;
        return oPlural.mCount == mCount && mInterpolation.equals(oPlural.mInterpolation);
      }
      return false;
    }
  }

  class Context implements PreOperation {

    private final String mContextPrefix;

    public Context(String value) {
      mContextPrefix = I18Next.getInstance().getOptions().getContextPrefix() + value;
    }

    @Override
    public String preProcess(String key) {
      if (mContextPrefix != null && mContextPrefix.length() > 0 && key != null) {
        String keyWithContext = key + mContextPrefix;
        if (I18Next.getInstance().existValue(keyWithContext)) {
          key = keyWithContext;
        }
      }
      return key;
    }

    @Override
    public String preProcessAfterNoValueFound(String key) {
      return null;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Context) {
        return I18Next.equalsCharSequence(mContextPrefix, ((Context) o).mContextPrefix);
      }
      return false;
    }
  }

  class SPrintF implements PostOperation {

    private final Object[] mArgs;

    public SPrintF(Object... args) {
      mArgs = args;
    }

    @Override
    public String postProcess(String source) {
      if (source != null && mArgs != null && mArgs.length > 0) {
        source = String.format(source, mArgs);
      }
      return source;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof SPrintF) {
        Object[] otherArgs = ((SPrintF) o).mArgs;
        int nbElement = (mArgs == null) ? 0 : mArgs.length;
        int nbElementOther = (otherArgs == null) ? 0 : otherArgs.length;
        if (nbElement == nbElementOther) {
          if (nbElement != 0) {
            for (int i = 0; i < nbElement; i++) {
              Object arg1 = mArgs[i];
              Object arg2 = otherArgs[i];
              if ((arg1 != null && !arg1.equals(arg2)) || (arg1 == null && arg2 == null)) {
                return false;
              }
            }
          }
          return true;
        }
      }
      return false;
    }
  }

  class Interpolation implements PostOperation {

    private final StringBuffer mStringBuffer = new StringBuffer();
    private final Map<String, Object> mReplacementMap;

    private static Map<String, Object> getReplacementMap(CharSequence target,
        CharSequence replacement) {
      Map<String, Object> map = new HashMap<>();
      map.put(target.toString(), replacement);
      return map;
    }

    public Interpolation(CharSequence target, CharSequence replacement) {
      this(getReplacementMap(target, replacement));
    }

    public Interpolation(Map<String, Object> replacementMap) {
      mReplacementMap = replacementMap;
    }

    @Override
    public String postProcess(String source) {
      if (source != null) {
        Options options = I18Next.getInstance().getOptions();
        String prefix = options.getInterpolationPrefix();
        int prefixLength = prefix.length();
        String suffix = options.getInterpolationSuffix();

        int lastIndexPrefix = -1;
        while (true) {
          int prefixIndex = source.indexOf(prefix, lastIndexPrefix);
          if (prefixIndex < 0) {
            break;
          } else {
            int suffixIndex = source.indexOf(suffix, prefixIndex + prefixLength);
            if (suffixIndex < 0) {
              break;
            }
            String target = source.substring(prefixIndex + prefixLength, suffixIndex);
            Object replacement = getObject(target);
            if (replacement == null) {
              lastIndexPrefix = suffixIndex + 1; // skip this replacement
            } else {
              mStringBuffer.setLength(0);
              mStringBuffer.append(prefix);
              mStringBuffer.append(target);
              mStringBuffer.append(suffix);
              source = source.replace(mStringBuffer.toString(),
                  replacement.toString());
              lastIndexPrefix = 0;
            }
          }
        }
      }
      return source;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Interpolation) {
        return mReplacementMap != null && mReplacementMap
            .equals(((Interpolation) o).mReplacementMap);
      }
      return false;
    }

    private Object getObject(String target) {
      if (target != null) {
        String[] targetParts = target.split("\\.");
        if (targetParts.length > 0) {
          Object rootObject = mReplacementMap;
          for (String part : targetParts) {
            if (rootObject instanceof Map<?, ?>) {
              rootObject = ((Map<?, ?>) rootObject).get(part);
            } else {
              I18Next.LOG.warn("Impossible to replace '{}': not found", target);
              return null;
            }
          }
          return rootObject;
        }
      }
      return null;
    }
  }

  class DefaultValue implements PostOperation {

    private final String mValue;

    public DefaultValue(String value) {
      mValue = value;
    }

    @Override
    public String postProcess(String source) {
      if (source == null) {
        source = mValue;
      }
      return source;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof DefaultValue) {
        return I18Next.equalsCharSequence(mValue, ((DefaultValue) o).mValue);
      }
      return false;
    }
  }
}
