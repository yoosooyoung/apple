// vue.config.js 파일에 설정 추가

const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
	transpileDependencies: true,
	lintOnSave: false,
	outputDir: '../src/main/resources/static', // 빌드 타겟 디렉토리
	devServer: {
		proxy: {
			// 로컬 서버 테스트 시 이용
			'/api': {
				// '/api' 로 들어오면 포트 8081(스프링 서버)로 proxy 처리
				target: 'http://localhost:8090',
				changeOrigin: true, // cross origin 허용
			},
		},
	},
});
