import React from 'react';
import ReactDOM from 'react-dom';

class HelloWorld {

	static main() {

		/* JSX의 노드는 꼭 대문자로 시작해야 합니다. */
		var HelloWorld = React.createClass({
			render: function() {
				return (
					<div>
						<p>Hello World!!</p>
					</div>
				);
			}
		});

		ReactDOM.render(
			<HelloWorld />,
			document.getElementById('app')
		)
	}
}

HelloWorld.main();