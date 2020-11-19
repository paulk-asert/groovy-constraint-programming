--[[

  SPDX-License-Identifier: Apache-2.0

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.

--]]

local m = 1
for o = 0, 9 do
  for n = 0, 9 do
    for e = 0, 9 do
      for y = 0, 9 do
        for s = 0, 9 do
          for d = 0, 9 do
            for r = 0, 9 do
              local send=s*1000+e*100+n*10+d*1;
              local more=m*1000+o*100+r*10+e*1;
              local money=m*10000+o*1000+n*100+e*10+y*1;
              if (send+more==money) and
                (s~=e) and (s~=n) and (s~=d) and (s~=m) and
                (s~=o) and (s~=r) and (s~=y) and (e~=n) and
                (e~=d) and (e~=m) and (e~=o) and (e~=r) and
                (e~=y) and (n~=d) and (n~=m) and (n~=o) and
                (n~=r) and (n~=y) and (d~=m) and (d~=o) and
                (d~=r) and (d~=y) and (m~=o) and (m~=r) and
                (m~=y) and (o~=r) and (o~=y) and (r~=y) then
                print(send, more, money)
              end
            end
          end
        end
      end
    end
  end
end
