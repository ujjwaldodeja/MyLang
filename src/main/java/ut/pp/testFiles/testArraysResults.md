Note that un-commenting anything gives a contextual error (as it should).

### Output:
```
Warning at line 12:4 -> Array y not initialized
Sprockell 0 says 0
Sprockell 0 says 2
Sprockell 0 says 1
Sprockell 0 says 1
Sprockell 0 says 0
Sprockell 0 says 1
Sprockell 0 says 0
Sprockell 0 says 1
Sprockell 0 says 0
Sprockell 0 says 1
```

### Spril instructions:
```
  0: Store reg0 (DirAddr 0)                   --Declaration of m
  1: Load (ImmValue 1) regA                   --Assignment of array to x
  2: Store regA (DirAddr 1)                   
  3: Load (ImmValue 2) regA                   
  4: Store regA (DirAddr 2)                   
  5: Load (ImmValue 3) regA                   
  6: Store regA (DirAddr 3)                   
  7: Store reg0 (DirAddr 4)                   --Declaration of y
  8: Store reg0 (DirAddr 5)                   
  9: Store reg0 (DirAddr 6)                   
 10: Load (ImmValue 1) regA                   --Check if condition
 11: Compute Equal regA reg0 regA             
 12: Branch regA (Abs 16)                     
 13: Store reg0 (DirAddr 7)                   --Declaration of y // If body
 14: Load (DirAddr 7) regA                    --(Loading array index y at offset 0) // Print
 15: WriteInstr regA numberIO                 
 16: Load (DirAddr 1) regA                    --(Loading var x) // Assignment of array to y
 17: Store regA (DirAddr 4)                   
 18: Load (DirAddr 2) regA                    --(Loading var x)
 19: Store regA (DirAddr 5)                   
 20: Load (DirAddr 3) regA                    --(Loading var x)
 21: Store regA (DirAddr 6)                   
 22: Store reg0 (DirAddr 7)                   --Declaration of z
 23: Store reg0 (DirAddr 8)                   
 24: Store reg0 (DirAddr 9)                   
 25: Store reg0 (DirAddr 10)                  
 26: Load (ImmValue 0) regC                   --Check if condition
 27: Load (ImmValue 1) regB                   
 28: Load (DirAddr 1) regA                    --(Loading var x)
 29: Compute NEq regA regB regA               
 30: Compute Or regC regA regC                
 31: Load (ImmValue 2) regB                   
 32: Load (DirAddr 2) regA                    --(Loading var x)
 33: Compute NEq regA regB regA               
 34: Compute Or regC regA regC                
 35: Load (ImmValue 3) regB                   
 36: Load (DirAddr 3) regA                    --(Loading var x)
 37: Compute NEq regA regB regA               
 38: Compute Or regC regA regC                
 39: Compute And regC regC regA               
 40: Compute Equal regA reg0 regA             
 41: Branch regA (Abs 44)                     
 42: Load (ImmValue 9) regA                   --Print // If body
 43: WriteInstr regA numberIO                 
 44: Load (ImmValue 2) regA                   --Assignment to r
 45: Store regA (DirAddr 11)                  
 46: Load (ImmValue 1) regB                   --Calculating offset for reading from variable x // Assignment to k
 47: Load (DirAddr 11) regA                   --(Loading var r)
 48: Compute Sub regA regB regA               
 49: Compute Lt regA reg0 regB                
 50: Load (ImmValue 2) regC                   
 51: Compute Gt regA regC regC                
 52: Compute Or regB regC regB                
 53: Load (ImmValue 1) regF                   
 54: Branch regB (Abs 182)                    --Branch to error
 55: Load (ImmValue 1) regB                   
 56: Compute Add regA regB regA               
 57: Load (IndAddr regA) regA                 --(Loading array index x at calculated offset)
 58: Store regA (DirAddr 12)                  
 59: Load (DirAddr 12) regA                   --(Loading var k) // Print
 60: WriteInstr regA numberIO                 
 61: Load (ImmValue 1) regC                   --Print
 62: Load (ImmValue 1) regB                   
 63: Load (DirAddr 1) regA                    --(Loading var x)
 64: Compute Equal regA regB regA             
 65: Compute And regC regA regC               
 66: Load (ImmValue 2) regB                   
 67: Load (DirAddr 2) regA                    --(Loading var x)
 68: Compute Equal regA regB regA             
 69: Compute And regC regA regC               
 70: Load (ImmValue 3) regB                   
 71: Load (DirAddr 3) regA                    --(Loading var x)
 72: Compute Equal regA regB regA             
 73: Compute And regC regA regC               
 74: Compute And regC regC regA               
 75: WriteInstr regA numberIO                 
 76: Load (ImmValue 0) regC                   --Print
 77: Load (ImmValue 1) regB                   
 78: Load (ImmValue 1) regA                   
 79: Compute NEq regA regB regA               
 80: Compute Or regC regA regC                
 81: Load (ImmValue 2) regB                   
 82: Load (ImmValue 2) regA                   
 83: Compute NEq regA regB regA               
 84: Compute Or regC regA regC                
 85: Load (ImmValue 4) regB                   
 86: Load (ImmValue 3) regA                   
 87: Compute NEq regA regB regA               
 88: Compute Or regC regA regC                
 89: Compute And regC regC regA               
 90: WriteInstr regA numberIO                 
 91: Load (ImmValue 0) regC                   --Print
 92: Load (ImmValue 1) regB                   
 93: Load (ImmValue 1) regA                   
 94: Compute NEq regA regB regA               
 95: Compute Or regC regA regC                
 96: Load (ImmValue 2) regB                   
 97: Load (ImmValue 2) regA                   
 98: Compute NEq regA regB regA               
 99: Compute Or regC regA regC                
100: Load (ImmValue 3) regB                   
101: Load (ImmValue 3) regA                   
102: Compute NEq regA regB regA               
103: Compute Or regC regA regC                
104: Compute And regC regC regA               
105: WriteInstr regA numberIO                 
106: Load (ImmValue 0) regC                   --Print
107: Load (ImmValue (-1)) regB                
108: Load (ImmValue 1) regA                   
109: Compute NEq regA regB regA               
110: Compute Or regC regA regC                
111: Load (ImmValue 2) regB                   
112: Load (ImmValue 2) regA                   
113: Compute NEq regA regB regA               
114: Compute Or regC regA regC                
115: Load (ImmValue 3) regB                   
116: Load (ImmValue 3) regA                   
117: Compute NEq regA regB regA               
118: Compute Or regC regA regC                
119: Compute And regC regC regA               
120: WriteInstr regA numberIO                 
121: Load (ImmValue 1) regC                   --Print
122: Load (ImmValue 1) regB                   
123: Load (ImmValue 1) regA                   
124: Compute Equal regA regB regA             
125: Compute And regC regA regC               
126: Load (ImmValue 2) regB                   
127: Load (ImmValue 2) regA                   
128: Compute Equal regA regB regA             
129: Compute And regC regA regC               
130: Load (ImmValue 4) regB                   
131: Load (ImmValue 3) regA                   
132: Compute Equal regA regB regA             
133: Compute And regC regA regC               
134: Compute And regC regC regA               
135: WriteInstr regA numberIO                 
136: Load (ImmValue 1) regC                   --Print
137: Load (ImmValue 1) regB                   
138: Load (ImmValue 1) regA                   
139: Compute Equal regA regB regA             
140: Compute And regC regA regC               
141: Load (ImmValue 2) regB                   
142: Load (ImmValue 2) regA                   
143: Compute Equal regA regB regA             
144: Compute And regC regA regC               
145: Load (ImmValue 3) regB                   
146: Load (ImmValue 3) regA                   
147: Compute Equal regA regB regA             
148: Compute And regC regA regC               
149: Compute And regC regC regA               
150: WriteInstr regA numberIO                 
151: Load (ImmValue 1) regC                   --Print
152: Load (ImmValue (-1)) regB                
153: Load (ImmValue 1) regA                   
154: Compute Equal regA regB regA             
155: Compute And regC regA regC               
156: Load (ImmValue 2) regB                   
157: Load (ImmValue 2) regA                   
158: Compute Equal regA regB regA             
159: Compute And regC regA regC               
160: Load (ImmValue 3) regB                   
161: Load (ImmValue 3) regA                   
162: Compute Equal regA regB regA             
163: Compute And regC regA regC               
164: Compute And regC regC regA               
165: WriteInstr regA numberIO                 
166: Load (ImmValue 1) regC                   --Print
167: Load (DirAddr 4) regB                    --(Loading var y)
168: Load (DirAddr 1) regA                    --(Loading var x)
169: Compute Equal regA regB regA             
170: Compute And regC regA regC               
171: Load (DirAddr 5) regB                    --(Loading var y)
172: Load (DirAddr 2) regA                    --(Loading var x)
173: Compute Equal regA regB regA             
174: Compute And regC regA regC               
175: Load (DirAddr 6) regB                    --(Loading var y)
176: Load (DirAddr 3) regA                    --(Loading var x)
177: Compute Equal regA regB regA             
178: Compute And regC regA regC               
179: Compute And regC regC regA               
180: WriteInstr regA numberIO                 
181: EndProg                                  
182: Load (ImmValue 0) regA                   --Error Handling
183: Compute NEq regF regA regB               
184: Branch regB (Rel 51)                     
185: Load (ImmValue 69) regA                  --Print error DivByZero
186: WriteInstr regA charIO                   
187: Load (ImmValue 114) regA                 
188: WriteInstr regA charIO                   
189: Load (ImmValue 114) regA                 
190: WriteInstr regA charIO                   
191: Load (ImmValue 111) regA                 
192: WriteInstr regA charIO                   
193: Load (ImmValue 114) regA                 
194: WriteInstr regA charIO                   
195: Load (ImmValue 58) regA                  
196: WriteInstr regA charIO                   
197: Load (ImmValue 32) regA                  
198: WriteInstr regA charIO                   
199: Load (ImmValue 67) regA                  
200: WriteInstr regA charIO                   
201: Load (ImmValue 97) regA                  
202: WriteInstr regA charIO                   
203: Load (ImmValue 110) regA                 
204: WriteInstr regA charIO                   
205: Load (ImmValue 110) regA                 
206: WriteInstr regA charIO                   
207: Load (ImmValue 111) regA                 
208: WriteInstr regA charIO                   
209: Load (ImmValue 116) regA                 
210: WriteInstr regA charIO                   
211: Load (ImmValue 32) regA                  
212: WriteInstr regA charIO                   
213: Load (ImmValue 100) regA                 
214: WriteInstr regA charIO                   
215: Load (ImmValue 105) regA                 
216: WriteInstr regA charIO                   
217: Load (ImmValue 118) regA                 
218: WriteInstr regA charIO                   
219: Load (ImmValue 105) regA                 
220: WriteInstr regA charIO                   
221: Load (ImmValue 100) regA                 
222: WriteInstr regA charIO                   
223: Load (ImmValue 101) regA                 
224: WriteInstr regA charIO                   
225: Load (ImmValue 32) regA                  
226: WriteInstr regA charIO                   
227: Load (ImmValue 98) regA                  
228: WriteInstr regA charIO                   
229: Load (ImmValue 121) regA                 
230: WriteInstr regA charIO                   
231: Load (ImmValue 32) regA                  
232: WriteInstr regA charIO                   
233: Load (ImmValue 48) regA                  
234: WriteInstr regA charIO                   
235: Load (ImmValue 1) regA                   
236: Compute NEq regF regA regB               
237: Branch regB (Rel 113)                    
238: Load (ImmValue 69) regA                  --Print error ArrayIndexOutOfBounds
239: WriteInstr regA charIO                   
240: Load (ImmValue 114) regA                 
241: WriteInstr regA charIO                   
242: Load (ImmValue 114) regA                 
243: WriteInstr regA charIO                   
244: Load (ImmValue 111) regA                 
245: WriteInstr regA charIO                   
246: Load (ImmValue 114) regA                 
247: WriteInstr regA charIO                   
248: Load (ImmValue 58) regA                  
249: WriteInstr regA charIO                   
250: Load (ImmValue 32) regA                  
251: WriteInstr regA charIO                   
252: Load (ImmValue 65) regA                  
253: WriteInstr regA charIO                   
254: Load (ImmValue 116) regA                 
255: WriteInstr regA charIO                   
256: Load (ImmValue 116) regA                 
257: WriteInstr regA charIO                   
258: Load (ImmValue 101) regA                 
259: WriteInstr regA charIO                   
260: Load (ImmValue 109) regA                 
261: WriteInstr regA charIO                   
262: Load (ImmValue 112) regA                 
263: WriteInstr regA charIO                   
264: Load (ImmValue 116) regA                 
265: WriteInstr regA charIO                   
266: Load (ImmValue 105) regA                 
267: WriteInstr regA charIO                   
268: Load (ImmValue 110) regA                 
269: WriteInstr regA charIO                   
270: Load (ImmValue 103) regA                 
271: WriteInstr regA charIO                   
272: Load (ImmValue 32) regA                  
273: WriteInstr regA charIO                   
274: Load (ImmValue 116) regA                 
275: WriteInstr regA charIO                   
276: Load (ImmValue 111) regA                 
277: WriteInstr regA charIO                   
278: Load (ImmValue 32) regA                  
279: WriteInstr regA charIO                   
280: Load (ImmValue 97) regA                  
281: WriteInstr regA charIO                   
282: Load (ImmValue 99) regA                  
283: WriteInstr regA charIO                   
284: Load (ImmValue 99) regA                  
285: WriteInstr regA charIO                   
286: Load (ImmValue 101) regA                 
287: WriteInstr regA charIO                   
288: Load (ImmValue 115) regA                 
289: WriteInstr regA charIO                   
290: Load (ImmValue 115) regA                 
291: WriteInstr regA charIO                   
292: Load (ImmValue 32) regA                  
293: WriteInstr regA charIO                   
294: Load (ImmValue 111) regA                 
295: WriteInstr regA charIO                   
296: Load (ImmValue 117) regA                 
297: WriteInstr regA charIO                   
298: Load (ImmValue 116) regA                 
299: WriteInstr regA charIO                   
300: Load (ImmValue 32) regA                  
301: WriteInstr regA charIO                   
302: Load (ImmValue 111) regA                 
303: WriteInstr regA charIO                   
304: Load (ImmValue 102) regA                 
305: WriteInstr regA charIO                   
306: Load (ImmValue 32) regA                  
307: WriteInstr regA charIO                   
308: Load (ImmValue 98) regA                  
309: WriteInstr regA charIO                   
310: Load (ImmValue 111) regA                 
311: WriteInstr regA charIO                   
312: Load (ImmValue 117) regA                 
313: WriteInstr regA charIO                   
314: Load (ImmValue 110) regA                 
315: WriteInstr regA charIO                   
316: Load (ImmValue 100) regA                 
317: WriteInstr regA charIO                   
318: Load (ImmValue 115) regA                 
319: WriteInstr regA charIO                   
320: Load (ImmValue 32) regA                  
321: WriteInstr regA charIO                   
322: Load (ImmValue 105) regA                 
323: WriteInstr regA charIO                   
324: Load (ImmValue 110) regA                 
325: WriteInstr regA charIO                   
326: Load (ImmValue 100) regA                 
327: WriteInstr regA charIO                   
328: Load (ImmValue 101) regA                 
329: WriteInstr regA charIO                   
330: Load (ImmValue 120) regA                 
331: WriteInstr regA charIO                   
332: Load (ImmValue 32) regA                  
333: WriteInstr regA charIO                   
334: Load (ImmValue 111) regA                 
335: WriteInstr regA charIO                   
336: Load (ImmValue 102) regA                 
337: WriteInstr regA charIO                   
338: Load (ImmValue 32) regA                  
339: WriteInstr regA charIO                   
340: Load (ImmValue 97) regA                  
341: WriteInstr regA charIO                   
342: Load (ImmValue 114) regA                 
343: WriteInstr regA charIO                   
344: Load (ImmValue 114) regA                 
345: WriteInstr regA charIO                   
346: Load (ImmValue 97) regA                  
347: WriteInstr regA charIO                   
348: Load (ImmValue 121) regA                 
349: WriteInstr regA charIO                   
350: EndProg                     
``` 