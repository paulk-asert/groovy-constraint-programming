/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// WARNING: Nashorn is removed from JDK15
import javax.script.ScriptEngineManager

def engine = new ScriptEngineManager().getEngineByName('nashorn')
def script = '''
var Range = Java.type("groovy.lang.IntRange");
var Set = Java.type("java.util.HashSet");
var m = 1;
var o = 0;
var others = new Range(2, 9);
for each (s in [8, 9]) {
  for each (e in others) {
    for each (n in others) {
      for each (d in others) {
        for each (r in others) {
          for each (y in others) {
             if (new Set([s, e, n, d, r, y]).size() == 6 &&
                           s*1000 + e*100 + n*10 + d +
                           m*1000 + o*100 + r*10 + e -
                 m*10000 - o*1000 - n*100 - e*10 - y == 0) {
                 print([s, e, n, d]);
                 print([m, o, r, e]);
                 print([m, o, n, e, y]);
             }
          }
        }
      }
    }
  }
}
'''

engine.eval(script)
