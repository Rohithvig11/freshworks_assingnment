<h1 align="center">File Based Key-Value Data Store</h1>
<h3 align="center">A java application for file based Key-Value Data Store that performs basic CRD Operations</h3>
<h3>key points : </h3>
<ol>
<li>
<h4>file storage</h4>
     <ul>
     <li>If we give a file path as the input then key-value pair is stored there.</li>
     <li>If we give"NA" then it will be stored in default location</li>
     </ul>
</li>
<li>     
<h4>Junit test</h4>
     <ul>
     <li>I have included 4 tests</li>
     <li>One for testing success failure cases of create function.</li>
     <li>One for testing success failure cases of read function.</li>
     <li>One for testing success failure cases of delete function.</li>
     <li>One for testing the time to leave feature(ttl).</li>
</ul></li>
<li>
<h4>input</h4>
     <ul>
     <li>I have given the sample key value pair.</li>
     <li>I have commented the code to read input from user,if needed we can uncomment.</li>
     </ul>
</li>
</ol>
<h3>CRD Methods : </h3>
<ol>
<li>
<h4>create(String key,JSONObject value,int ttl):</h4>
     <ul>
     <li>three arguments-> key, value and Time to Live</li>
     <li>This methods checks if the key already exists or not,if exists return "key already exists".</li>
          <li>if the key size is greather than 32chars return "key size is more than 32 chars".</li>
          <li>else stores the key value in the file and return "successfully created"</li>
     </ul>
</li>
<li>
<h4>read(String key):</h4>
     <ul>
     <li>one argument-> key</li>
          <li>This methods checks if the key exists or not,If not exists return "Key not found".</li>
          <li>else returns the value stored in the file for the given key</li></ul>
</li>
<li>
<h4>delete(String key):</h4>
     <ul>
     <li>one argument-> key</li>
     <li>This methods checks if the key exists or not,If not exists return "Key not found".</li> 
     <li>else deletes the value with the given key.</li></ul>
</li>
</ol>
