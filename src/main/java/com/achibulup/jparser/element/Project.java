package com.achibulup.jparser.element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Project {
  private Map<String, Package> packageMap = new HashMap<>();

  /**
   * Add a package with coresponding name.
   * @param packageFullName : the name of the package to add
   * @return the added package or the existing package associated with the given name
   */
  public Package addPackage(String packageFullName) {
    if (packageMap.containsKey(packageFullName)) {
      return packageMap.get(packageFullName);
    }
    Package newPackage = new Package(packageFullName);
    packageMap.put(packageFullName, newPackage);
    return newPackage;
  }

  public Iterable<Package> getPackages() {
    return new Iterable<Package>() {
      final Iterator<Map.Entry<String, Package>> iter = packageMap.entrySet().iterator();
      @Override
      public Iterator<Package> iterator() {
        Iterator<Map.Entry<String, Package>> base = iter;
        return new Iterator<Package>() {
          @Override
          public boolean hasNext() {
            return base.hasNext();
          }

          @Override
          public Package next() {
            return base.next().getValue();
          }
        };
      }
    };
  }
}
